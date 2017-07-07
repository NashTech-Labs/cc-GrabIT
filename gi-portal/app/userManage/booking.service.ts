/**
 * Created by knoldus on 7/7/17.
 */
import {Injectable} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {UserModel} from '../_models/userModel';
import {devEnvironment} from '../environments/environment.dev';
import {prodEnvironment} from '../environments/environment.prod';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';
import 'rxjs/Observable';


@Injectable()
export class BookingService {

    constructor(private http: Http) {
    }
}