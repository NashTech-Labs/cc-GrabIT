import {Component, OnInit} from "@angular/core";
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
                private alertService: AlertService) {
    }

    index: string;
    user: UserModel = new UserModel('', '', '', '');
    returnedUseraddResponse: any;
    userData: any = [];
    formValues: any = [];
    isEmployeeIdAvailable: any;
    isEmailIdAvailable: any;

    private roles = [
        "admin", "employee"
    ];

    ngOnInit() {
        this.updateUserList();
    }

    /**
     * isEmpIdExitsCheck method to check whether the entered employeeId already exists in db
     */
    isEmpIdExistsCheck() {
        this.usersService.isEmpIdExists(this.user.employeeId).subscribe(
            (data) => {
                this.isEmployeeIdAvailable = data
            }
        )
    }

    /**
     * isEmailExits method to check whether the entered email already exists in db
     */
    isEmailExists() {
        this.usersService.isEmailExists(this.user.emailId).subscribe(
            (data) => {
                this.isEmailIdAvailable = data
            }
        )
    }

    /**
     * updatedUserList method to reload the users list and display on OnInit
     */
    updateUserList() {
        // Getting the list of users when users view appear
        this.usersService.getUserList().subscribe(
            (data) => {
                this.userData = data
            },
            error => {
                if (error.status === 0) {
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
                );
                this.updateUserList();
                jQuery(".modal-body input").val("");
                jQuery('#newUserModal').modal('hide');
            },
            error => {
                if (error.status === 0) {
                    swal(
                        'Error Occurred',
                        'Uh!! Some Issue, Please try again or check your connections.',
                        'error'
                    )
                } else if (this.isEmailIdAvailable) {
                    swal(
                        'Error Occurred',
                        'EmailId already exists, please try with different one',
                        'error'
                    )
                } else if (this.isEmployeeIdAvailable) {
                    swal(
                        'Error Occurred',
                        'EmployeeId already exists, please try with different one',
                        'error'
                    )
                }
            }
        )
    }

    /**
     * resetForm method to remove the values in form when press close
     */
    resetForm() {
        jQuery('form').trigger('reset');
    }
}
