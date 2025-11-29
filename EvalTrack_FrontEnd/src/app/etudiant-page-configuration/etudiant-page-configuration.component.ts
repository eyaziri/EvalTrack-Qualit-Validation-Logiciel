import { Component } from '@angular/core';
import { SideBarComponent } from '../side-bar/side-bar.component';
import { HeaderComponent } from '../header/header.component';
import { ContentComponent } from "../content/content.component";
import { HeaderStandardComponent } from "../header-standard/header-standard.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-etudiant-page-configuration',
  imports: [SideBarComponent, ContentComponent, HeaderStandardComponent,HeaderComponent],
  templateUrl: './etudiant-page-configuration.component.html',
  styleUrl: './etudiant-page-configuration.component.scss'
})
export class EtudiantPageConfigurationComponent {

}
