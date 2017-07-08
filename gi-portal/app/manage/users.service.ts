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
export class UsersService {

    constructor(private http: Http) {
    }

    /**
     *  Change environment var as per the environments
     * @type {{production: boolean; loginApiUrl: string; userApiUrl: string; assetApiUrl: string}}
     */
       environment = devEnvironment;
    // environment = prodEnvironment;

    /**
     * Api urls to interact with backend services
     * @type {string|any}
     */
    private currentUserStorage = localStorage.getItem('currentUser');
    private accessToken = JSON.parse(this.currentUserStorage).accessToken;

    /**
     * Api urls of backend
     * @type {string}
     */
    private listUsersApi = this.environment.userApiUrl + 'user/get/all?accessToken=' + this.accessToken;
    private addUserApi = this.environment.userApiUrl + 'user/add?accessToken=' + this.accessToken;


    /**
     * Method to check whether entered employeeId already exists or not
     * @param empId
     * @returns {Observable<R>}
     */
    isEmpIdExists(empId: any) {
        return this.http.get(this.environment.userApiUrl + 'user/employeeid/exists?employeeId=' + empId + '&accessToken=' + this.accessToken).map(
            (response: Response) => response.json()
        )
    }

    isEmailExists(emailId: any) {
        return this.http.get(this.environment.userApiUrl + 'user/email/exists?email=' + emailId + '&accessToken=' + this.accessToken).map(
            (response: Response) => response.json()
        )
    }

    /**
     * AddUser method for adding new employee/admin
     * @param user
     * @returns {Observable<R>}
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
                //return this.extractData(data)
                data;
            })
    }

    /**
     * Getting the list of admin/employees
     * @returns {Observable<R>}
     */
    getUserList() {
        return this.http.get(this.listUsersApi).map(
            (response: Response) => response.json()
        )
        // .catch(this._errorHandler);
    }

    _errorHandler(error: Response) {
        // return Observable.throw(error || "Server Error");
    }

    /**
     * Extracting responded data from server
     * @param res
     * @returns {any}
     */
    extractData(res: any) {
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
    }
}