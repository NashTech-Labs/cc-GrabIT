/**
 * Created by knoldus on 26/6/17.
 */

import {Component, OnInit} from "@angular/core";
import {ChartComponent} from './chart.component';
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {UserModel} from '../_models/userModel'
import { UsersService } from '../manage/users.service';
import {ActivatedRoute, Router} from "@angular/router";
import { Observable }     from 'rxjs/Observable';
import {log} from "util";



@Component({
    selector:'dashboard',
    templateUrl:'app/home/dashboard.component.html',
    styleUrls: ['../../app/assets/css/dashboard.component.css']
})

export class DashboardComponent implements OnInit{

    private currentUserStorage = localStorage.getItem('currentUser');
    private role = JSON.parse(this.currentUserStorage).role;


    ngOnInit(){

    }

}
