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
import { ColDef } from 'ag-grid-community'; // Column Definition Type Interface


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
  public isOn: Boolean = true;

  public legendSettings?: Object;
  pagination = true;
  paginationPageSize = 500;
  paginationPageSizeSelector = [10, 200, 500, 1000];
  rowData = [
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
    { make: "Tesla", model: "Model Y", price: 64950, electric: true },
    { make: "Ford", model: "F-Series", price: 33850, electric: false },
    { make: "Toyota", model: "Corolla", price: 29600, electric: false },
  ];
  // Column Definitions: Defines the columns to be displayed.
  colDefs: ColDef[] = [
    { field: "make", filter: true, editable: true },
    { field: "model", filter: true, editable: true },
    { field: "price", filter: true, editable: true },
    { field: "electric", filter: true, editable: true }
  ];

  public pointClick(args: IPointEventArgs): void {
    this.isOn = !this.isOn
    console.log(args)
  };

  public edit(event: any): void {
    console.log(event)

  };

  ngOnInit() {
    this.piedata = pieData;
    this.legendSettings = {
        visible: false
    };
    this.webSocketService.listen("currencyData").subscribe((data) => {
      console.log(data);
    })
  }

  ngOnDestroy(): void {
    this.webSocketService.disconnect();
  }


}
