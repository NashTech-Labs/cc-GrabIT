import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {UserModel} from '../_models/userModel'
import { UsersService } from './users.service';
import {ActivatedRoute, Router} from "@angular/router";
import { Observable }     from 'rxjs/Observable';
import {log} from "util";



@Component({
  selector:'userform',
  templateUrl:'app/manage/users.component.html',
  styles:[`
      input.ng-invalid{border-left: 5px solid red}
      input.ng-valid{border-left:5px solid green}
  `]
})

export class UserComponent implements OnInit{


constructor (private usersService: UsersService, private route: ActivatedRoute, private router: Router){}

   index: string;
   user: UserModel = new UserModel('', '', '', '');
   returnedUseraddResponse:any;
   userData:any=[];
   formValues:any=[];


  ngOnInit(){
    // Getting the list of users
     this.usersService.getUserList().subscribe(
        (data) => {this.userData = data
         console.log("data in component >>>>")
         console.log(data)
        }
      )
    }

  onSubmit(value:any) {
      this.usersService.addUser(this.user).subscribe((data: any) => {
       this.returnedUseraddResponse = data;
        alert('User Added')
        this.formValues = value;
        console.log("Form Submitted values : "+ JSON.stringify(this.formValues));
        //this.router.navigate(['/home']);
      })
  }
}
