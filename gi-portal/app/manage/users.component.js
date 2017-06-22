"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var UserComponent = (function () {
    function UserComponent() {
        //  index: string;
        //   myTask: Task[];
        //   task: Task = new Task('', '', '', '', '');
        //creating this property for pre-filled value in form
        this.myName = "Nikhil Kumar";
        this.formValues = [];
    }
    UserComponent.prototype.ngOnInit = function () {
    };
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
    UserComponent.prototype.onSubmit = function (value) {
        console.log(value);
        alert("submitted successfully");
        this.formValues = value;
        console.log("Form Submitted values : " + JSON.stringify(this.formValues));
    };
    return UserComponent;
}());
UserComponent = __decorate([
    core_1.Component({
        selector: 'userform',
        templateUrl: 'app/manage/users.component.html',
        styles: ["\n      input.ng-invalid{border-left: 5px solid red}\n      input.ng-valid{border-left:5px solid green}\n  "]
    })
], UserComponent);
exports.UserComponent = UserComponent;
//# sourceMappingURL=users.component.js.map