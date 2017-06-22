import { NgModule }      from '@angular/core';
import { HttpModule }  from '@angular/http';
import { CommonModule }  from '@angular/common';
import {HomeComponent} from './home.component';
import {homeRouting} from './home.routes';
import {SidebarComponent} from './sidebar.component';


@NgModule({
  imports:      [ homeRouting, HttpModule, CommonModule ],
  declarations: [ HomeComponent,SidebarComponent]

})
export class HomeModule {}
