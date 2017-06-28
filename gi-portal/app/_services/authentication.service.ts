import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';
import 'rxjs/Observable';


@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(email: string, password: string) {
        return this.http.post('http://localhost:9999/signin', JSON.stringify({ email: email, password: password }))
            .map((response: Response) => {
                // login successful if there's a token in the response
                let user = response.json();

                console.log("Access token:::::::::::::::::"+ JSON.stringify(user));
                if (user.accessToken) {
                    // store user details and  token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    var currentUserStorage = localStorage.getItem('currentUser');
                    var accessToken = JSON.parse(currentUserStorage).accessToken;
                }
                return user;
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}