import { NgModule }      from '@angular/core';
import { DatePipe }      from '@angular/common';
import { HttpModule }  from '@angular/http';
import { manageRouting }       from './manage.route';
import { UserService }       from './user.service';
 
import { UserComponent } from './users.component'


@NgModule({
  imports:      [ manageRouting, HttpModule ],
  declarations: [  UserComponent],
  providers:    [ UserService, DatePipe]
})
export class ManageModule {}
