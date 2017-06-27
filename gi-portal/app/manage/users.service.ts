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
import 'rxjs/Observable';



@Injectable()
export class UsersService {

constructor(private http:Http) {}


    private currentUserStorage = localStorage.getItem('currentUser');
    private accessToken = JSON.parse(this.currentUserStorage).accessToken;

    // Api urls of backend
    private listUsersApi = 'http://localhost:9999/user/get/all?accessToken='+this.accessToken;
    private addUserApi = 'http://localhost:9999/user/add?accessToken='+this.accessToken;



    /*
     * addUser method to add the user
     */

    addUser(user: UserModel): Observable<any> {
        let jsonHeader = new Headers({
          'Content-Type': 'application/json'
        });
        let obj = {
          name: user.name,
          email: user.emailId,
          employeeId: user.employeeId,
          role: user.role
        };
        return this.http.post(this.addUserApi, obj, {headers: jsonHeader})
          .map(data => {
            return this.eaxtractData(data)
          })
      }

   // getting the list of user
    getUserList(){
          return this.http.get(this.listUsersApi).map(
            (response: Response) => response.json()
          )
        // .catch(this._errorHandler);
      }

    _errorHandler(error: Response){
        console.error("Problem in service:::: " + error);
        // return Observable.throw(error || "Server Error");
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
      // return Observable.throw(new Error(errMsg));
    }
}