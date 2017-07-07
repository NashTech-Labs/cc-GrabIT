import { NgModule }      from '@angular/core';
import { DatePipe }      from '@angular/common';
import { HttpModule }  from '@angular/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {userManageRouting} from "./userManage.routes";
import {BookingService} from "./booking.service";
import {BookingComponent} from "./booking.component";


@NgModule({
    imports:      [ userManageRouting, HttpModule, FormsModule, CommonModule],
    declarations: [ BookingComponent ],
    providers:    [ DatePipe, BookingService]
})
export class UserManageModule {}
