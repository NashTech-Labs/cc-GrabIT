"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var http_1 = require("@angular/http");
var manage_route_1 = require("./manage.route");
var users_service_1 = require("./users.service");
var forms_1 = require("@angular/forms");
var common_2 = require("@angular/common");
var users_component_1 = require("./users.component");
var assets_component_1 = require("./assets.component");
var assets_service_1 = require("./assets.service");
var ManageModule = (function () {
    function ManageModule() {
    }
    return ManageModule;
}());
ManageModule = __decorate([
    core_1.NgModule({
        imports: [manage_route_1.manageRouting, http_1.HttpModule, forms_1.FormsModule, common_2.CommonModule],
        declarations: [users_component_1.UserComponent, assets_component_1.AssetsComponent],
        providers: [users_service_1.UsersService, common_1.DatePipe, assets_service_1.AssetsService]
    })
], ManageModule);
exports.ManageModule = ManageModule;
//# sourceMappingURL=manage.module.js.map