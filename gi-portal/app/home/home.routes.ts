import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from '../home/index';
import { AuthGuard } from '../_guards/index';
import { SidebarComponent } from '../home/sidebar.component'
import { UserComponent } from '../manage/users.component';
import { DashboardComponent } from './dashboard.component';
import {AssetsComponent} from "../manage/assets.component";

const routes:Routes = [ 
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuard],
        children: [
            {
                path: 'manage/user',
                component: UserComponent
            },
            {
                path: 'manage/assets',
                component: AssetsComponent
            },
            {
                path: 'dashboard',
                component: DashboardComponent
            }
        ]
    }
];
export const homeRouting = RouterModule.forChild(routes);
