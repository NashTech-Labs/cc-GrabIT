import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators, FormBuilder} from "@angular/forms";

@Component({
  selector:'userform',
  templateUrl:'app/manage/users.component.html',
  styles:[`
      input.ng-invalid{border-left: 5px solid red}
      input.ng-valid{border-left:5px solid green}
  `]
})

export class UserComponent implements OnInit{

  constructor(){}

  userFormMDF: FormGroup;

  formValues:any=[];

  ngOnInit(){
    // this.userFormMDF = this._formbuilder.group({
    //     name: ['Nikhil',[Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
    //     email:['abc@abc.com', [Validators.required, Validators.email]],
     
    //   address: this._formbuilder.group({
    //     street: [null, [Validators.required]],
    //     city:[null],
    //     postal:[null, [Validators.required]]
    //   })
    // })
  }

  //Commented this code because we are using formcontrol many times and that is not a feasable solution,
  // for this we will use formbuilder approach above.

  /*
  userFormMDF = new FormGroup({
    name : new FormControl('Nikhil Kumar', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
    email: new FormControl('abc@abc.in',[Validators.required, Validators.email]),
    address: new FormGroup({
      street: new FormControl(null, Validators.required),
      city: new FormControl(),
      postal: new FormControl(null, Validators.required)
    })
  });
  */

  onSubmit(){
    // console.log(this.userFormMDF.value);
    // this.formValues = this.userFormMDF.value;

  }
}
