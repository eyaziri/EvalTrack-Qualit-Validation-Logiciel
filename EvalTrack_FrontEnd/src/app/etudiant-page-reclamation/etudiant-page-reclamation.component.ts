import { Component } from '@angular/core';
import { SideBarComponent } from "../side-bar/side-bar.component";
import { HeaderStandardComponent } from "../header-standard/header-standard.component";
import { ContentReclamationComponent } from "../content-reclamation/content-reclamation.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-etudiant-page-reclamation',
  imports: [SideBarComponent, HeaderStandardComponent, ContentReclamationComponent,],
  templateUrl: './etudiant-page-reclamation.component.html',
  styleUrl: './etudiant-page-reclamation.component.scss'
})
export class EtudiantPageReclamationComponent {

}
