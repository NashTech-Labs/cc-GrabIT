import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from '../home/index';
import { AuthGuard } from '../_guards/index';
import { SidebarComponent } from '../home/sidebar.component'
import { UserComponent } from '../manage/users.component';

const routes:Routes = [ 
    {
        path: 'home',
        component: HomeComponent,
        children: [
            {
                path: 'manage/user',
                component: UserComponent
            }
        ]
    }
];
export const homeRouting = RouterModule.forChild(routes);
