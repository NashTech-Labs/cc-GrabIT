"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var SidebarComponent = (function () {
    function SidebarComponent(elementRef) {
        this.elementRef = elementRef;
    }
    SidebarComponent.prototype.ngOnInit = function () {
        jQuery(this.elementRef.nativeElement).find('#menu-toggle').on('click', function (e) {
            e.preventDefault();
            jQuery("#wrapper").toggleClass("toggled");
        });
    };
    return SidebarComponent;
}());
SidebarComponent = __decorate([
    core_1.Component({
        selector: 'admin-sidebar',
        moduleId: module.id,
        templateUrl: 'sidebar.component.html',
        styleUrls: ['sidebar.component.css']
    }),
    __metadata("design:paramtypes", [core_1.ElementRef])
], SidebarComponent);
exports.SidebarComponent = SidebarComponent;
//# sourceMappingURL=sidebar.component.js.map