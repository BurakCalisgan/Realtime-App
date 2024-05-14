import { Component } from '@angular/core';import { DialogModule, TooltipModule } from '@syncfusion/ej2-angular-popups';
import { ChartModule, AccumulationChartModule, RangeNavigatorModule, SparklineModule, SmithchartModule, StockChartModule, BulletChartModule, Chart3DModule, CircularChart3DModule } from '@syncfusion/ej2-angular-charts';

import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { NavbarComponent } from './components/navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [DialogModule, TooltipModule, ChartModule, AccumulationChartModule, RangeNavigatorModule, SparklineModule, SmithchartModule, StockChartModule, BulletChartModule, Chart3DModule, CircularChart3DModule, CommonModule, RouterOutlet, MatButtonModule, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'client';
}
