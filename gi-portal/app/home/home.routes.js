"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var index_1 = require("../home/index");
var index_2 = require("../_guards/index");
var users_component_1 = require("../manage/users.component");
var dashboard_component_1 = require("./dashboard.component");
var routes = [
    {
        path: 'home',
        component: index_1.HomeComponent,
        canActivate: [index_2.AuthGuard],
        children: [
            {
                path: 'manage/user',
                component: users_component_1.UserComponent
            },
            {
                path: 'dashboard',
                component: dashboard_component_1.DashboardComponent
            }
        ]
    }
];
exports.homeRouting = router_1.RouterModule.forChild(routes);
//# sourceMappingURL=home.routes.js.map