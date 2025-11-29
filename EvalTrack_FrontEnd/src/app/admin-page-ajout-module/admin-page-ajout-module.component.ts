import { Component } from '@angular/core';
import { SideBarAdminComponent } from "../side-bar-admin/side-bar-admin.component";
import { HeaderStandardComponent } from "../header-standard/header-standard.component";
import { AddModulesComponent } from "../components/add-modules/add-modules.component";


@Component({
  selector: 'app-admin-page-ajout-module',
  imports: [SideBarAdminComponent, HeaderStandardComponent, AddModulesComponent],
  templateUrl: './admin-page-ajout-module.component.html',
  styleUrl: './admin-page-ajout-module.component.scss'
})
export class AdminPageAjoutModuleComponent {

}
