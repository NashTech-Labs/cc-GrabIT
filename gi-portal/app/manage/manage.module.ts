import { NgModule }      from '@angular/core';
import { DatePipe }      from '@angular/common';
import { HttpModule }  from '@angular/http';
import { manageRouting }       from './manage.route';
import { UsersService }       from './users.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


import { UserComponent } from './users.component'


@NgModule({
  imports:      [ manageRouting, HttpModule, FormsModule, CommonModule],
  declarations: [  UserComponent],
  providers:    [ UsersService, DatePipe]
})
export class ManageModule {}
