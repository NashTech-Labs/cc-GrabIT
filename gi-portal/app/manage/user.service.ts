import { Injectable }     from '@angular/core';
import { Http, Response, Headers} from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import {UserModel} from '../_models/userModel';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class UserService {

constructor(private http:Http) {}


    private listUsersApi = '/api/';
    private addUserApi = '/api/';


    addUser(user: UserModel): Observable<any> {
    let jsonHeader = new Headers({
      'Content-Type': 'application/json'
    });
    let obj = {
      name: user.name,
      emailId: user.emailId,
      employeeId: user.employeeId,
      role: user.role
    };
    return this.http.post(this.addUserApi, obj, {headers: jsonHeader})
      .map(data => {
        return this.eaxtractData(data)
      })
      .catch((e: any) => {
        return this.handle(e)
      });
  }

  
  eaxtractData(res: any) {
    let body = res.json();
    return body;
  }

  private handle(error: any) {
    let errMsg: string;
    try {
      if (JSON.parse(error._body).message) {
        errMsg = JSON.parse(error._body).message
      } else {
        errMsg = 'Some thing went wrong';
      }

    }
    catch (e) {
      errMsg = 'Somthing Went Wrong try again!!'
    }
    return Observable.throw(new Error(errMsg));
  }



}