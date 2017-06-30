/**
 * Created by knoldus on 29/6/17.
 */
import {Component, ElementRef, OnInit} from "@angular/core";
import {UserModel} from '../_models/userModel'
import {AssetsService} from './assets.service'
import {ActivatedRoute, Router} from "@angular/router";
import {AssetsModel} from "../_models/assetsModel";
declare var jQuery: any;
declare var swal: any;

@Component({
    selector: 'assets',
    templateUrl: 'app/manage/assets.component.html',
    styleUrls: ['app/assets/css/assets.component.css']
})

export class AssetsComponent implements OnInit {

    constructor(private route: ActivatedRoute, private router: Router, elementRef: ElementRef, private assetsService:AssetsService) {
    }

    assetsData: any = [];
    asset: AssetsModel = new AssetsModel('', '', '');
    returnedAssetAddResponse:any =[];
    formValues: any = [];


    ngOnInit() {
        // Getting the list of assets when assets view appear
        this.assetsService.getAssetsList().subscribe(
            (data) => {
                this.assetsData = data;
            }
        )
    }

    /**
     * onSubmit method to store the new asset's details
     * @param value
     */
    onSubmit(value: any) {
        this.assetsService.addAsset(this.asset).subscribe((data: any) => {
            this.returnedAssetAddResponse = data;
            this.formValues = value;
            swal(
                'Good job!',
                'New asset has been added!',
                'success'
            )
            jQuery(".modal-body input").val("");
            jQuery('#newAssetModal').modal('hide');
        })
    }

}