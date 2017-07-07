import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from '../home/index';
import { AuthGuard } from '../_guards/index';
import { UserComponent } from '../manage/users.component';
import { DashboardComponent } from './dashboard.component';
import {AssetsComponent} from "../manage/assets.component";
import {BookingComponent} from "../userManage/booking.component";

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
            },
            {
                path: 'manage/booking',
                component: BookingComponent
            }

        ]
    }
];
export const homeRouting = RouterModule.forChild(routes);
