import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SideBarAdminComponent } from '../side-bar-admin/side-bar-admin.component';

@Component({
  selector: 'app-gestion-filiere',
  standalone: true,
  imports: [CommonModule, FormsModule, SideBarAdminComponent],
  templateUrl: './gestion-filiere.component.html',
  styleUrls: ['./gestion-filiere.component.scss']
})
export class GestionFiliereComponent {
  filieres: string[] = ['Informatique', 'Infotronique', 'Mecatronique', 'GSIL'];
  nouvelleFiliere: string = '';
  filiereEnModification: number | null = null;
  filiereModifiee: string = '';

  ajouterFiliere() {
    if (this.nouvelleFiliere.trim()) {
      this.filieres.push(this.nouvelleFiliere.trim());
      this.nouvelleFiliere = '';
    }
  }

  supprimerFiliere(index: number) {
    if (confirm(`Êtes-vous sûr de vouloir supprimer la filière "${this.filieres[index]}" ?`)) {
      this.filieres.splice(index, 1);
    }
  }

  modifierFiliere(index: number) {
    this.filiereEnModification = index;
    this.filiereModifiee = this.filieres[index];
  }

  enregistrerModification() {
    if (this.filiereEnModification !== null && this.filiereModifiee.trim()) {
      this.filieres[this.filiereEnModification] = this.filiereModifiee.trim();
      this.annulerModification();
    }
  }

  annulerModification() {
    this.filiereEnModification = null;
    this.filiereModifiee = '';
  }
}
