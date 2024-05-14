import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebSocketService } from '../../services/web-socket.service';
import {
  AccumulationChartModule, PieSeriesService, AccumulationLegendService, AccumulationTooltipService, AccumulationAnnotationService,
  AccumulationDataLabelService, IPointEventArgs
} from '@syncfusion/ej2-angular-charts';
import { DialogModule, TooltipModule } from '@syncfusion/ej2-angular-popups';
import { pieData } from './datasource';
import { AgGridAngular } from 'ag-grid-angular'; // Angular Data Grid Component
import { ColDef,GridApi, GridOptions,GridReadyEvent, ModuleRegistry } from 'ag-grid-community'; // Column Definition Type Interface


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DialogModule, TooltipModule, AccumulationChartModule, CommonModule, AgGridAngular],
  providers: [PieSeriesService, AccumulationLegendService, AccumulationTooltipService, AccumulationDataLabelService, AccumulationAnnotationService],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit, OnDestroy {

  webSocketService = inject(WebSocketService)

  public piedata?: Object[];
  public legendSettings?: Object;
  public startAngle?: number;
  public endAngle?: number;
  public datalabel?: Object;
  public isOn: Boolean = true;


  private gridApi!: GridApi<any>;
  public socketData?: any;
  pagination = true;
  paginationPageSize = 500;
  paginationPageSizeSelector = [10, 200, 500, 1000];
  // Column Definitions: Defines the columns to be displayed.
  colDefs: ColDef[] = [
    { field: "currency", filter: true, editable: true },
    { field: "rate", filter: true, editable: true },
  ];
  public rowData = [
    { currency: "USD", rate: 15.5 },
    { currency: "EUR", rate: 19.5 },
  ];


  public pointClick(args: IPointEventArgs): void {
    this.isOn = !this.isOn
    console.log(args)
  };

  public edit(event: any): void {
    console.log(event)

  };

  onGridReady(params: GridReadyEvent<any>) {
    this.gridApi = params.api;
  }

  ngOnInit() {
    this.piedata = pieData;
    this.startAngle = 0;
    this.endAngle = 360;
    this.datalabel = { visible: true, name: 'text', position: 'Outside' };
    this.legendSettings = {
      visible: true
    };

    this.webSocketService.listen("currencyData").subscribe((data) => {
      const index = this.rowData.findIndex(x => x.currency === data.currency);

      if (index !== -1) {
        // Mevcut satırı güncelle
        const updatedRow = { ...this.rowData[index], rate: parseFloat(data.rate) };
        this.rowData[index] = updatedRow;

         // AG Grid'e değişiklikleri bildir
         this.gridApi.setGridOption("rowData",this.rowData);
      } else {
         // Yeni satır ekle
         const newCurrencyValue = {
          currency: data.currency,
          rate: parseFloat(data.rate)
        };
        this.rowData.push(newCurrencyValue);

        // AG Grid'e yeni satır ekle
        this.gridApi.setGridOption("rowData",this.rowData);
      }
      this.socketData = JSON.stringify(data);
      console.log(data);
    });
  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }

}
