import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";
import {UserModel} from '../_models/userModel'

@Component({
  selector:'userform',
  templateUrl:'app/manage/users.component.html',
  styles:[`
      input.ng-invalid{border-left: 5px solid red}
      input.ng-valid{border-left:5px solid green}
  `]
})

export class UserComponent implements OnInit{

//  index: string;
//   myTask: Task[];
//   task: Task = new Task('', '', '', '', '');

  ngOnInit(){
  }

  //creating this property for pre-filled value in form
  myName="Nikhil Kumar";

  formValues:any=[];

  // pushTask() {
  //   if (this.index) {
  //     //this.service.update(this.index, this.task);
  //     // this.service.remove(this.task._id).subscribe((data: any) => alert(JSON.stringify(data)));
  //     this.service.updateTask(this.task).subscribe((data: any) => {
  //       alert('Task Updated')
  //       this.router.navigate(['show']);
  //     }, err => {
  //       console.error(err);
  //     })
  //   } else {
  //     //this.service.add(this.task);
  //     this.service.addTask(this.task).subscribe((data: any) => {
  //       alert('Task Added')
  //       this.router.navigate(['show']);
  //     }, err => {
  //       console.error(err);
  //     })
  //   }
  // }


  onSubmit(value: any){
        console.log(value);
        alert("submitted successfully");
        this.formValues = value;
        console.log("Form Submitted values : "+ JSON.stringify(this.formValues));
  }
}
