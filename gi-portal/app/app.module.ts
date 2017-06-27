import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import { CommonModule } from '@angular/common';

// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';
import { ManageModule } from './manage/manage.module'
import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { AlertService, AuthenticationService, UserService } from './_services/index';
import { HomeComponent } from './home/index';
import { HomeModule } from './home/home.modue';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { SidebarComponent } from './home/sidebar.component';
import { ChartsModule } from 'ng2-charts';



@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing,
        ManageModule,
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
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }