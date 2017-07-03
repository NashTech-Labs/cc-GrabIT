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
var index_1 = require("../_services/index");
var UserComponent = (function () {
    function UserComponent(usersService, route, router, alertService, elementRef) {
        this.usersService = usersService;
        this.route = route;
        this.router = router;
        this.alertService = alertService;
        this.user = new userModel_1.UserModel('', '', '', 'ADMINNN');
        this.userData = [];
        this.formValues = [];
        this.roles = [
            "admin", "employee"
        ];
    }
    UserComponent.prototype.ngOnInit = function () {
        this.updateUserList();
    };
    UserComponent.prototype.updateUserList = function () {
        var _this = this;
        // Getting the list of users when users view appear
        this.usersService.getUserList().subscribe(function (data) {
            _this.userData = data;
        }, function (error) {
            if (error.status === 0) {
                swal('Error Occurred', 'Uh!! Some issue, Please try again or check your connections.', 'error');
            }
            else {
                swal('Error Occurred', error._body, 'error');
            }
        });
    };
    /**
     * onSubmit method to store the new employee/admin details
     * @param value
     */
    UserComponent.prototype.onSubmit = function (value) {
        var _this = this;
        this.usersService.addUser(this.user).subscribe(function (data) {
            _this.returnedUseraddResponse = data;
            _this.formValues = value;
            swal('Good job!', 'New user has been added!', 'success');
            _this.updateUserList();
            jQuery(".modal-body input").val("");
            jQuery('#newUserModal').modal('hide');
        }, function (error) {
            if (error.status === 0) {
                swal('Error Occurred', 'Uh!! Some Issue, Please try again or check your connections.', 'error');
            }
            else {
                swal('Error Occurred', error._body, 'error');
            }
        });
    };
    UserComponent.prototype.resetForm = function () {
        jQuery('form').trigger('reset');
    };
    return UserComponent;
}());
UserComponent = __decorate([
    core_1.Component({
        selector: 'userform',
        templateUrl: 'app/manage/users.component.html',
        styleUrls: ['app/assets/css/user.component.css']
    }),
    __metadata("design:paramtypes", [users_service_1.UsersService, router_1.ActivatedRoute, router_1.Router,
        index_1.AlertService, core_1.ElementRef])
], UserComponent);
exports.UserComponent = UserComponent;
//# sourceMappingURL=users.component.js.map