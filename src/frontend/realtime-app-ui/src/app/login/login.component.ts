import { Component } from '@angular/core';
import { ChartModule } from '@syncfusion/ej2-angular-charts';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ChartModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  ///----------
  public data: Object[] = [
    { country: 'USD', value: 10 },
    { country: 'EUR', value: 20 },
    { country: 'GBP', value: 30 },
    { country: 'JPY', value: 40 }
  ];
  public title: string = 'Döviz Değerleri';
  public legendSettings: Object =
    {
      visible: true,
      position: 'bottom'
    };

}
