/**
 * Created by knoldus on 29/6/17.
 */
import {Injectable}     from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable}     from 'rxjs/Observable';
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
import {AssetsModel} from "../_models/assetsModel";


@Injectable()
export class AssetsService {

    constructor(private http: Http) {
    }

    /**
     * Change environment var as per environment
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

    private listAssets = this.environment.assetApiUrl+'asset/get/all?accessToken=' + this.accessToken;
    private addAssets = this.environment.assetApiUrl+'asset/add?accessToken=' + this.accessToken;


    /**
     * addAsset method for adding new asset
     * @param user
     * @returns {Observable<R>}
     */
    addAsset(asset: AssetsModel): Observable<any> {
        let jsonHeader = new Headers({
            'Content-Type': 'application/json'
        });
        let obj = {
            name: asset.name,
            uniqueName: asset.uniqueName,
            assetType: asset.assetType
        };
        return this.http.post(this.addAssets, obj, {headers: jsonHeader})
            .map(data => {
                data;
            })
    }

    /**
     * getAssetsList method to list the assets available
     * @returns {Observable<R>}
     */
    getAssetsList() {
        return this.http.get(this.listAssets).map(
            (response: Response) => response.json()
        )
        // .catch(this._errorHandler);
    }
}