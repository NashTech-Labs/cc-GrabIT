"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var index_1 = require("../home/index");
var users_component_1 = require("../manage/users.component");
var routes = [
    {
        path: 'home',
        component: index_1.HomeComponent,
        children: [
            {
                path: 'manage/user',
                component: users_component_1.UserComponent
            }
        ]
    }
];
exports.homeRouting = router_1.RouterModule.forChild(routes);
//# sourceMappingURL=home.routes.js.map