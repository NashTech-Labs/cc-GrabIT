import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';
import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { ManageModule } from './manage/manage.module'
import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { AlertService, AuthenticationService, UserService } from './_services/index';
import { HomeModule } from './home/home.modue';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { ChartsModule } from 'ng2-charts';
import {UserManageModule} from "./userManage/userManage.module";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        ManageModule,
        UserManageModule,
        HomeModule,
        CommonModule,
        ChartsModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        LoginComponent,
        RegisterComponent
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,

        // providers used to create fake backend
        // fakeBackendProvider,
        // MockBackend,
        // BaseRequestOptions
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }