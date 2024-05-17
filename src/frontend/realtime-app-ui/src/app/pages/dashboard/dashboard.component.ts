import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebSocketService } from '../../services/web-socket.service';
import {
  AccumulationChartModule, PieSeriesService, AccumulationLegendService, AccumulationTooltipService, AccumulationAnnotationService,
  AccumulationDataLabelService, IPointEventArgs
} from '@syncfusion/ej2-angular-charts';
import { DialogModule, TooltipModule } from '@syncfusion/ej2-angular-popups';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { ColDef, GridApi, GridOptions, GridReadyEvent, ModuleRegistry } from 'ag-grid-community'; // Column Definition Type Interface
import { SymbolService } from '../../services/symbol.service';
import { SymbolResponse } from '../../interfaces/response/symbol-reponse';
import { SymbolUpdateRequest } from '../../interfaces/request/symbol-update-request';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { CurrencyInfoService } from '../../services/currency-info.service';
import { CurrencyInfoResponse } from '../../interfaces/response/currency-info-reponse';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DialogModule, TooltipModule, AccumulationChartModule, CommonModule, AgGridAngular],
  providers: [PieSeriesService, AccumulationLegendService, AccumulationTooltipService, AccumulationDataLabelService, AccumulationAnnotationService],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  snackBar = inject(MatSnackBar);

  webSocketService = inject(WebSocketService)
  symbolService = inject(SymbolService)
  currencyInfoService = inject(CurrencyInfoService)


  public piedata?: Object[];
  public currencyDetail!: CurrencyInfoResponse;
  public legendSettings?: Object;
  public startAngle?: number;
  public endAngle?: number;
  public datalabel?: Object;
  public isOn: Boolean = true;

  fethPieData() {
    this.currencyInfoService.getCurrencies().subscribe(response => {
      this.piedata = response.map(item => {
        return {
          x: item.currency,
          y: item.dailyTransaction,
          fill: '#498fff',
          text: item.description
        };
      });
    });
  }

  fetchCurrencyDetailForDialog(currency: string) {
    this.currencyInfoService.getCurrencyDetail(currency).subscribe(response => {
      this.currencyDetail = response;
    });
  }

  pieInit() {
    this.fethPieData();
    //this.piedata = dataMapping;
    this.startAngle = 0;
    this.endAngle = 360;
    this.datalabel = { visible: true, name: 'text', position: 'Outside' };
    this.legendSettings = {
      visible: true
    };
  }


  public pointClick(args: IPointEventArgs): void {
    this.isOn = !this.isOn
    const dataSource: any = args.series.dataSource;
    this.fetchCurrencyDetailForDialog(dataSource[args.pointIndex].x);

  };

  onClose(args: any): void {
    this.isOn = !this.isOn
  }


  private gridApi!: GridApi<any>;
  public socketData?: any;
  pagination = true;
  paginationPageSize = 500;
  paginationPageSizeSelector = [10, 200, 500, 1000];
  // Column Definitions: Defines the columns to be displayed.
  colDefs: ColDef[] = [
    { headerName: "Id", field: "id", hide: true },
    { headerName: "Sembol", field: "symbol", filter: true, editable: false },
    { headerName: "Alış", field: "buyPrice", filter: true, editable: true },
    { headerName: "Satış", field: "sellPrice", filter: true, editable: true },
  ];

  public rowData!: SymbolResponse[];

  public edit(event: any): void {

    console.log(event);

    const symbolUpdateRequest: SymbolUpdateRequest = {
      id: event.data.id,
      symbol: event.data.symbol,
      buyPrice: event.data.buyPrice,
      sellPrice: event.data.sellPrice
    };

    this.symbolService.updateSymbol(symbolUpdateRequest).subscribe({
      next: (response: { message: string }) => {
        this.snackBar.open('Updated Successfully.', 'Close', {
          duration: 3000,
        });
      },
      error: (error: HttpErrorResponse) => {
        this.snackBar.open(error.message, 'Close', {
          duration: 3000,
        });
      },
    });

  };

  onGridReady(params: GridReadyEvent<any>) {
    this.gridApi = params.api;
  }


  fetchGridData() {
    this.symbolService.getSymbols().subscribe(response => {
      this.rowData = response;
    });
  }

  fetcWebSocketData() {
    this.webSocketService.listen("symbolData").subscribe((data) => {
      const index = this.rowData.findIndex(x => x.symbol === data.symbol);

      if (index !== -1) {
        // Mevcut satırı güncelle
        const updatedRow = { ...this.rowData[index], buyPrice: parseFloat(data.buyPrice), sellPrice: parseFloat(data.sellPrice) };
        this.rowData[index] = updatedRow;
        this.symbolService.updateSymbol(updatedRow).subscribe({
          next: (response: { message: string }) => {
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          },
        });

        // AG Grid'e değişiklikleri bildir
        this.gridApi.setGridOption("rowData", this.rowData);
      }
      // else {
      //   // Yeni satır ekle
      //   const newCurrencyValue = {
      //     id: data.id,
      //     symbol: data.symbol,
      //     buyPrice: parseFloat(data.buyPrice),
      //     sellPrice: parseFloat(data.sellPrice)
      //   };
      //   this.rowData.push(newCurrencyValue);

      //   // AG Grid'e yeni satır ekle
      //   this.gridApi.setGridOption("rowData", this.rowData);
      // }
      this.socketData = JSON.stringify(data);
      console.log(data);
    });

  }

  ngOnInit() {
    this.pieInit();

    this.fetchGridData();
    this.fetcWebSocketData();

  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }

}
