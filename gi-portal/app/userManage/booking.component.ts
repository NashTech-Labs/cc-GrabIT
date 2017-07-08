/**
 * Created by knoldus on 7/7/17.
 */

import {Component, ElementRef, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {AssetsModel} from "../_models/assetsModel";
import {BookingService} from "./booking.service";
declare var jQuery: any;
declare var swal: any;

@Component({
    selector: 'booking',
    templateUrl: 'app/userManage/booking.component.html',
    styleUrls: ['app/assets/css/assets.component.css']
})

export class BookingComponent implements OnInit {

    constructor(private route: ActivatedRoute, private router: Router, elementRef: ElementRef, private bookingService: BookingService) {
    }
    assetsList:any=[];


    ngOnInit() {
        // this.updateAssetList();
    }
}
