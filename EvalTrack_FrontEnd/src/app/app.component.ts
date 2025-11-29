import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { EtudiantPageConfigurationComponent } from "./etudiant-page-configuration/etudiant-page-configuration.component";
import { EtudiantPageReclamationComponent } from "./etudiant-page-reclamation/etudiant-page-reclamation.component";
import { EtudiantPageDashboardComponent } from "./etudiant-page-dashboard/etudiant-page-dashboard.component";
import { AdminPageListeEtudiantAdminComponent } from "./admin-page-liste-etudiant-admin/admin-page-liste-etudiant-admin.component";
import { AdminPageReclamationComponent } from "./admin-page-reclamation/admin-page-reclamation.component";
import { AdminPageConfigurationComponent } from "./admin-page-configuration/admin-page-configuration.component";
import { AdminPageNotesComponent } from "./admin-page-notes/admin-page-notes.component";
import { AdminPageAjoutModuleComponent } from "./admin-page-ajout-module/admin-page-ajout-module.component";
import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgotpassword/forgotpassword.component';
@Component({
  selector: 'app-root',
  standalone: true,

  imports: [RouterOutlet, LoginComponent, ForgotPasswordComponent, EtudiantPageConfigurationComponent, EtudiantPageReclamationComponent, EtudiantPageDashboardComponent, AdminPageListeEtudiantAdminComponent, AdminPageReclamationComponent, AdminPageConfigurationComponent, AdminPageNotesComponent, AdminPageAjoutModuleComponent,RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'EvalTrack_Frontend';
}
