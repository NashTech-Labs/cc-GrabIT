"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var common_1 = require("@angular/common");
var app_component_1 = require("./app.component");
var app_routing_1 = require("./app.routing");
var manage_module_1 = require("./manage/manage.module");
var index_1 = require("./_directives/index");
var index_2 = require("./_guards/index");
var index_3 = require("./_services/index");
var home_modue_1 = require("./home/home.modue");
var index_4 = require("./login/index");
var index_5 = require("./register/index");
var ng2_charts_1 = require("ng2-charts");
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    core_1.NgModule({
        imports: [
            platform_browser_1.BrowserModule,
            forms_1.FormsModule,
            http_1.HttpModule,
            app_routing_1.routing,
            manage_module_1.ManageModule,
            home_modue_1.HomeModule,
            common_1.CommonModule,
            ng2_charts_1.ChartsModule
        ],
        declarations: [
            app_component_1.AppComponent,
            index_1.AlertComponent,
            index_4.LoginComponent,
            index_5.RegisterComponent
        ],
        providers: [
            index_2.AuthGuard,
            index_3.AlertService,
            index_3.AuthenticationService,
            index_3.UserService,
        ],
        bootstrap: [app_component_1.AppComponent]
    })
], AppModule);
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map