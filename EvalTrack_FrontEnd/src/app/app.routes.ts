import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgotpassword/forgotpassword.component';
import { EtudiantPageDashboardComponent } from './etudiant-page-dashboard/etudiant-page-dashboard.component';
import { EtudiantPageConfigurationComponent } from './etudiant-page-configuration/etudiant-page-configuration.component';
import { EtudiantPageReclamationComponent } from './etudiant-page-reclamation/etudiant-page-reclamation.component';
import { AdminPageListeEtudiantAdminComponent } from './admin-page-liste-etudiant-admin/admin-page-liste-etudiant-admin.component';
import { AdminPageReclamationComponent } from './admin-page-reclamation/admin-page-reclamation.component'; // Admin
import { AdminPageConfigurationComponent } from './admin-page-configuration/admin-page-configuration.component'; // Admin
import { AdminPageNotesComponent } from './admin-page-notes/admin-page-notes.component'; // Admin
import { AdminPageAjoutModuleComponent } from './admin-page-ajout-module/admin-page-ajout-module.component'; // Admin
import { GestionFiliereComponent } from './gestion-filiere/gestion-filiere.component';
import { RoleGuard } from './role.guard'; // Import du guard
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { ModifierNoteComponent } from './modifier-note/modifier-note.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'forgotpassword', component: ForgotPasswordComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'access-denied', component: AccessDeniedComponent },
  {
    path: 'etudiant-page-dashboard',
    component: EtudiantPageDashboardComponent,
    canActivate: [RoleGuard],
    data: { role: '2' },
  },
  {
    path: 'etudiant-page-configuration',
    component: EtudiantPageConfigurationComponent,
    canActivate: [RoleGuard],
    data: { role: '2' },
  },
  {
    path: 'etudiant-page-reclamation',
    component: EtudiantPageReclamationComponent,
    canActivate: [RoleGuard],
    data: { role: '2' },
  },

  {
    path: 'admin/liste-etudiant',
    component: AdminPageListeEtudiantAdminComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'admin/reclamation',
    component: AdminPageReclamationComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'admin/configuration',
    component: AdminPageConfigurationComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'admin/notes',
    component: AdminPageNotesComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'modifie-note-etudiant',
    component: ModifierNoteComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'admin/ajout-module',
    component: AdminPageAjoutModuleComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
  {
    path: 'admin/gestion-filiere',
    component: GestionFiliereComponent,
    canActivate: [RoleGuard],
    data: { role: '1' },
  },
];
