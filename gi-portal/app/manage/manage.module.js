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
// import { SearchAssetComponent }    from './search-asset.component';
// import { AssetService } from './asset.service';
var manage_route_1 = require("./manage.route");
var user_service_1 = require("./user.service");
// import { SharedModule }       from '../shared/shared.module';
// import {AddAssetComponent} from './add-asset.component';
// import {EditAssetComponent} from './edit-asset.component';
// import {SearchAssetResolve} from './search-asset-resolve';
// import {AssignAssetComponent} from './assign-asset.component';
// import {ListComponent} from './list-asset.component';
// import {AgGridModule} from 'ag-grid-ng2/main';
// import {ListAssetResolve} from './list-asset-resolve';
// import {UserComponent} from './user.component';
// import { Ng2DatetimePickerModule } from 'ng2-datetime-picker';
// import {ListNewAssetComponent} from './list-new-asset.component';
// import {ListNewAssetResolve} from './list-new-asset-resolve';
// import {AssignAssetResolve} from './asset-assign-resolve';
var users_component_1 = require("./users.component");
var ManageModule = (function () {
    function ManageModule() {
    }
    return ManageModule;
}());
ManageModule = __decorate([
    core_1.NgModule({
        imports: [manage_route_1.manageRouting, http_1.HttpModule],
        declarations: [users_component_1.UserComponent],
        providers: [user_service_1.UserService, common_1.DatePipe]
    })
], ManageModule);
exports.ManageModule = ManageModule;
//# sourceMappingURL=manage.module.js.map