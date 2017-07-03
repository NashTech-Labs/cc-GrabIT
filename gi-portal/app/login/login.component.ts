import { Component,ElementRef, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, AuthenticationService } from '../_services/index';
declare var jQuery:any;
declare var swal: any;



@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls:['login.component.css']
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;
    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService,
        private elementRef: ElementRef
    ) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

        //sidebar menu toggle
        jQuery(this.elementRef.nativeElement).find('#menu-toggle').on('click', function(e:any){
            e.preventDefault();
            jQuery("#wrapper").toggleClass("toggled");
        })
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.email, this.model.password)
            .subscribe(
                data => {
                    // this.router.navigate([this.returnUrl]);
                    let userRole = data.role;
                    if(userRole === 'admin'){
                        this.router.navigate(['/home/manage/dashboard']);
                    }else {
                        this.router.navigate(['/home/user/dashboard']);
                    }
                },
                error => {
                    if(error.status === 0) {
                        swal(
                            'Error Occurred',
                            "Uh!! Some issue, Please try again or check your connections.",
                            'error'
                        )
                    } else {
                        swal(
                            'Error Occurred',
                            error._body,
                            'error'
                        )
                    }

                    this.loading = false;
                });
    }
}
