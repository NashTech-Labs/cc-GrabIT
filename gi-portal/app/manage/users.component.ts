import {Component, ElementRef, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {UserModel} from '../_models/userModel'
import { UsersService } from './users.service';
import {ActivatedRoute, Router} from "@angular/router";
import { Observable }     from 'rxjs/Observable';
import {log} from "util";
declare var jQuery:any;


@Component({
  selector:'userform',
  templateUrl:'app/manage/users.component.html',
  styles:[`
      input.ng-invalid{border-left: 5px solid red}
      input.ng-valid{border-left:5px solid green}
  `]
})

export class UserComponent implements OnInit{


constructor (private usersService: UsersService, private route: ActivatedRoute, private router: Router, elementRef: ElementRef){}

   index: string;
   user: UserModel = new UserModel('', '', '', '');
   returnedUseraddResponse:any;
   userData:any=[];
   formValues:any=[];
   elementRef: ElementRef;



  ngOnInit(){
    // Getting the list of users when users view appear
     this.usersService.getUserList().subscribe(
        (data) => {
            this.userData = data
        }
      )
    }

  onSubmit(value:any) {
      this.usersService.addUser(this.user).subscribe((data: any) => {
       this.returnedUseraddResponse = data;
        this.formValues = value;
        console.log(">>>>>>>>>>>>>>.."+ JSON.stringify(data));

          jQuery(this.elementRef.nativeElement).find('#menu-toggle').on('click', function(e:any){
              jQuery("#newUserModal").modal('hide');
          })

      })
  }
}
