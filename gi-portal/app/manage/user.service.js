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
var http_1 = require("@angular/http");
var Observable_1 = require("rxjs/Observable");
require("rxjs/add/operator/map");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/debounceTime");
require("rxjs/add/operator/distinctUntilChanged");
require("rxjs/add/operator/switchMap");
require("rxjs/add/operator/toPromise");
var UserService = (function () {
    function UserService(http) {
        this.http = http;
        this.listUsersApi = '/api/';
        this.addUserApi = '/api/';
    }
    UserService.prototype.addUser = function (user) {
        var _this = this;
        var jsonHeader = new http_1.Headers({
            'Content-Type': 'application/json'
        });
        var obj = {
            name: user.name,
            emailId: user.emailId,
            employeeId: user.employeeId,
            role: user.role
        };
        return this.http.post(this.addUserApi, obj, { headers: jsonHeader })
            .map(function (data) {
            return _this.eaxtractData(data);
        })
            .catch(function (e) {
            return _this.handle(e);
        });
    };
    UserService.prototype.eaxtractData = function (res) {
        var body = res.json();
        return body;
    };
    UserService.prototype.handle = function (error) {
        var errMsg;
        try {
            if (JSON.parse(error._body).message) {
                errMsg = JSON.parse(error._body).message;
            }
            else {
                errMsg = 'Some thing went wrong';
            }
        }
        catch (e) {
            errMsg = 'Somthing Went Wrong try again!!';
        }
        return Observable_1.Observable.throw(new Error(errMsg));
    };
    return UserService;
}());
UserService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], UserService);
exports.UserService = UserService;
//# sourceMappingURL=user.service.js.map