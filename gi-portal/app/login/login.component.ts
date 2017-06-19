import { Component, OnInit, trigger, state, style, transition, animate, Input } from '@angular/core';
//import initDemo = require('../../../assets/js/charts.js');
import {LoginService} from './login.service';
import { Router } from '@angular/router';
import 'rxjs/add/observable/throw';

declare var $:any;

@Component({
    selector: 'login-cmp',
    moduleId: module.id,
    styleUrls: ['./login.component.style.css'],
    templateUrl:'login.component.html'
})

export class LoginComponent implements OnInit{
    ngOnInit(){
        
        console.log("login rendered");
        // $('[data-toggle="checkbox"]').each(function () {
        //     if($(this).data('toggle') == 'switch') return;
        //
        //     var $checkbox = $(this);
        //     $checkbox.checkbox();
        // });
        //initDemo();
    }
}
