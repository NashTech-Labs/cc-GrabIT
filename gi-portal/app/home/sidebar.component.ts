import { Component, ElementRef, OnInit } from '@angular/core';
declare var jQuery:any;
import { User } from '../_models/user';
import { UserService } from '../_services/index';
import { Router } from '@angular/router';


@Component({
    selector: 'admin-sidebar',
    moduleId: module.id,
    templateUrl: 'sidebar.component.html',
    styleUrls:['sidebar.component.css']
})

export class SidebarComponent  implements OnInit {

elementRef: ElementRef;

constructor(elementRef: ElementRef){
    this.elementRef = elementRef;
}

    private currentUserStorage = localStorage.getItem('currentUser');
    private role = JSON.parse(this.currentUserStorage).role;

     ngOnInit():any {
      jQuery(this.elementRef.nativeElement).find('#menu-toggle').on('click', function(e:any){
          e.preventDefault();
          jQuery("#wrapper").toggleClass("toggled");
      })
    }
}