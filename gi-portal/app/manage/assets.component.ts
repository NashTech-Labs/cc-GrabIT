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
    private assetTypeList = [
        "projector", "meeting room", "printer"
    ];

    ngOnInit() {
        this.updateAssetList();
    }

    updateAssetList() {
        // Getting the list of assets when assets view appear
        this.assetsService.getAssetsList().subscribe(
            (data) => {
                this.assetsData = data;
            },
            error => {
                if(error.status === 0) {
                    swal(
                        'Error Occurred',
                        'Uh!! Some issue, Please try again or check your connections.',
                        'error'
                    )
                } else {
                    swal(
                        'Error Occurred',
                        error._body,
                        'error'
                    )
                }
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
                );
                this.updateAssetList();
                jQuery(".modal-body input").val("");
                jQuery('#newAssetModal').modal('hide');
            },
            error => {
                if(error.status === 0) {
                    swal(
                        'Error Occurred',
                        'Uh!! Some issue, Please try again or check your connections.',
                        'error'
                    )
                } else {
                    swal(
                        'Error Occurred',
                         error._body,
                        'error'
                    )
                }
            }
        )
    }

    resetForm() {
        jQuery('form').trigger('reset');
    }

}