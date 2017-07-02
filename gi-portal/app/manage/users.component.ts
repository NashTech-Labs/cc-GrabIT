import {Component, ElementRef, OnInit} from "@angular/core";
import {UserModel} from "../_models/userModel";
import {UsersService} from "./users.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AlertService} from "../_services/index";
declare var jQuery: any;
declare var swal: any;

@Component({
    selector: 'userform',
    templateUrl: 'app/manage/users.component.html',
    styleUrls: ['app/assets/css/user.component.css']
})

export class UserComponent implements OnInit {

    constructor(private usersService: UsersService, private route: ActivatedRoute, private router: Router,
                private alertService: AlertService, elementRef: ElementRef) {
    }

    index: string;
    user: UserModel = new UserModel('', '', '', 'ADMINNN');
    returnedUseraddResponse: any;
    userData: any = [];
    formValues: any = [];
    elementRef: ElementRef;
    private roles = [
        "admin", "employee"
    ];
    ngOnInit() {
        // Getting the list of users when users view appear
        this.usersService.getUserList().subscribe(
            (data) => {
                this.userData = data
            }
        )
    }

    /**
     * onSubmit method to store the new employee/admin details
     * @param value
     */
    onSubmit(value: any) {
        this.usersService.addUser(this.user).subscribe((data: any) => {
                this.returnedUseraddResponse = data;
                this.formValues = value;
                swal(
                    'Good job!',
                    'New user has been added!',
                    'success'
                )
                jQuery(".modal-body input").val("");
                jQuery('#newUserModal').modal('hide');
            },
            error => {
                swal(
                    'Error Ocuured',
                    error._body,
                    'error'
                )
            }
        )
    }
}
