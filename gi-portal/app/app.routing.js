"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var index_1 = require("./login/index");
var index_2 = require("./register/index");
var index_3 = require("./_guards/index");
var dashboard_component_1 = require("./home/dashboard.component");
var appRoutes = [
    { path: '', component: dashboard_component_1.DashboardComponent, canActivate: [index_3.AuthGuard] },
    // { path: '', redirectTo: 'home', pathMatch: 'full', canActivate: [AuthGuard]},
    { path: 'login', component: index_1.LoginComponent },
    { path: 'register', component: index_2.RegisterComponent },
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];
exports.routing = router_1.RouterModule.forRoot(appRoutes);
//# sourceMappingURL=app.routing.js.map