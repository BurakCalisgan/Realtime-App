
import { Component } from '@angular/core';
import { AgGridModule } from 'ag-grid-angular';
import { ColDef } from 'ag-grid-community';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [AgGridModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  columnDefs: ColDef[] = [
    { headerName: 'Döviz Kodu', field: 'currencyCode', sortable: true, filter: true },
    { headerName: 'Alış Fiyatı', field: 'buyPrice', sortable: true, filter: true },
    { headerName: 'Satış Fiyatı', field: 'sellPrice', sortable: true, filter: true }
  ];

  rowData = [
    { currencyCode: 'USD', buyPrice: 7.00, sellPrice: 7.20 },
    { currencyCode: 'EUR', buyPrice: 8.00, sellPrice: 8.20 },
    { currencyCode: 'GBP', buyPrice: 9.00, sellPrice: 9.20 },
    // dummy data
  ];


}
