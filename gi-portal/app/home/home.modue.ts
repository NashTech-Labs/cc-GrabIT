import { NgModule }      from '@angular/core';
import { HttpModule }  from '@angular/http';
import { CommonModule }  from '@angular/common';
import {HomeComponent} from './home.component';
import {homeRouting} from './home.routes';
import {SidebarComponent} from './sidebar.component';
import {DashboardComponent} from './dashboard.component';
import {ChartComponent} from './chart.component'
import { ChartsModule } from 'ng2-charts';


@NgModule({
  imports:      [ homeRouting, HttpModule, CommonModule, ChartsModule ],
  declarations: [ HomeComponent,SidebarComponent, DashboardComponent, ChartComponent]

})
export class HomeModule {}
