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
var environment_dev_1 = require("../environments/environment.dev");
require("rxjs/add/operator/map");
require("rxjs/add/operator/catch");
require("rxjs/add/operator/debounceTime");
require("rxjs/add/operator/distinctUntilChanged");
require("rxjs/add/operator/switchMap");
require("rxjs/add/operator/toPromise");
require("rxjs/Observable");
var AuthenticationService = (function () {
    function AuthenticationService(http) {
        this.http = http;
        /**
         *  Change environment var as per the environments
         * @type {{production: boolean; loginApiUrl: string; userApiUrl: string; assetApiUrl: string}}
         */
        this.environment = environment_dev_1.devEnvironment;
    }
    // environment = prodEnvironment;
    AuthenticationService.prototype.login = function (email, password) {
        return this.http.post(this.environment.loginApiUrl + 'signin', JSON.stringify({ email: email, password: password }))
            .map(function (response) {
            // login successful if there's a token in the response
            var user = response.json();
            if (user.accessToken) {
                // store user details and  token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                var currentUserStorage = localStorage.getItem('currentUser');
                var accessToken = JSON.parse(currentUserStorage).accessToken;
            }
            return user;
        });
    };
    AuthenticationService.prototype.logout = function () {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    };
    return AuthenticationService;
}());
AuthenticationService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], AuthenticationService);
exports.AuthenticationService = AuthenticationService;
//# sourceMappingURL=authentication.service.js.map