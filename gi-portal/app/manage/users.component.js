"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var UserComponent = (function () {
    function UserComponent() {
        this.formValues = [];
    }
    UserComponent.prototype.ngOnInit = function () {
        // this.userFormMDF = this._formbuilder.group({
        //     name: ['Nikhil',[Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
        //     email:['abc@abc.com', [Validators.required, Validators.email]],
        //   address: this._formbuilder.group({
        //     street: [null, [Validators.required]],
        //     city:[null],
        //     postal:[null, [Validators.required]]
        //   })
        // })
    };
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
    UserComponent.prototype.onSubmit = function () {
        // console.log(this.userFormMDF.value);
        // this.formValues = this.userFormMDF.value;
    };
    return UserComponent;
}());
UserComponent = __decorate([
    core_1.Component({
        selector: 'userform',
        templateUrl: 'app/manage/users.component.html',
        styles: ["\n      input.ng-invalid{border-left: 5px solid red}\n      input.ng-valid{border-left:5px solid green}\n  "]
    }),
    __metadata("design:paramtypes", [])
], UserComponent);
exports.UserComponent = UserComponent;
//# sourceMappingURL=users.component.js.map