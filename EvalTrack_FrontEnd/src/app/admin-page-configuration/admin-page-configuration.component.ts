import { Component } from '@angular/core';
import { SideBarAdminComponent } from "../side-bar-admin/side-bar-admin.component";
import { HeaderStandardComponent } from "../header-standard/header-standard.component";
import { EtudiantPageConfigurationComponent } from "../etudiant-page-configuration/etudiant-page-configuration.component";
import { ContentComponent } from "../content/content.component";
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-admin-page-configuration',
  imports: [SideBarAdminComponent, HeaderStandardComponent, EtudiantPageConfigurationComponent, ContentComponent,HeaderComponent],
  templateUrl: './admin-page-configuration.component.html',
  styleUrl: './admin-page-configuration.component.scss'
})
export class AdminPageConfigurationComponent {

}
