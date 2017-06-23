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
var userModel_1 = require("../_models/userModel");
var users_service_1 = require("./users.service");
var router_1 = require("@angular/router");
var UserComponent = (function () {
    function UserComponent(usersService, route, router) {
        this.usersService = usersService;
        this.route = route;
        this.router = router;
        this.user = new userModel_1.UserModel('', '', '', '');
        this.userData = [];
        this.formValues = [];
    }
    UserComponent.prototype.ngOnInit = function () {
        // Getting the list of users
        /*this.usersService.getUserList().subscribe(
           (data) => {this.userData = data
            console.log(data)}
         )*/
    };
    UserComponent.prototype.onSubmit = function (value) {
        var _this = this;
        this.usersService.addUser(this.user).subscribe(function (data) {
            _this.returnedUseraddResponse = data;
            alert('User Added');
            _this.formValues = value;
            console.log("Form Submitted values : " + JSON.stringify(_this.formValues));
            //this.router.navigate(['/home']);
        });
    };
    return UserComponent;
}());
UserComponent = __decorate([
    core_1.Component({
        selector: 'userform',
        templateUrl: 'app/manage/users.component.html',
        styles: ["\n      input.ng-invalid{border-left: 5px solid red}\n      input.ng-valid{border-left:5px solid green}\n  "]
    }),
    __metadata("design:paramtypes", [users_service_1.UsersService, router_1.ActivatedRoute, router_1.Router])
], UserComponent);
exports.UserComponent = UserComponent;
//# sourceMappingURL=users.component.js.map