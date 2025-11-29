import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReclamationService } from '../../Services/reclamation.service';  // Ajuste le chemin selon ton arborescence
import { Reclamation } from '../../Services/reclamation.service';

@Component({
  standalone: true,
  selector: 'app-tableau-reclamation',
  imports: [CommonModule, FormsModule],
  templateUrl: './tableau-reclamation.component.html',
  styleUrls: ['./tableau-reclamation.component.scss']
})
export class TableauReclamationComponent implements OnInit {
  reclamations: any[] = [];

  constructor(private reclamationService: ReclamationService) {}

  ngOnInit(): void {
    this.reclamationService.getAllReclamations().subscribe({
      next: (data) => {
       
        this.reclamations = data.map((r: any) => ({
          ...r,
          emailProf: '',
          emailEtudiant : r.etudiant.email
        }));
      },
      error: (err) => {
        console.error('Erreur de chargement des réclamations :', err);
      }
    });
  }

  envoyerEmailProf(reclamation: any) {
    const emailProf = reclamation.emailProf;
    const contenu = `
      Réclamation de ${reclamation.emailEtudiant}
      Type: ${reclamation.type}
      Matière: ${reclamation.matiereConcerne}
      Enseignant: ${reclamation.nomProf}
    `;
    alert(`Simuler envoi d'email à : ${emailProf}\nContenu :\n${contenu}`);
  }

  verifierNote(reclamation: Reclamation) {
    const idReclamation = reclamation.idReclamation ?? 0; // Fallback à 0 si undefined
    this.reclamationService.changerStatut(idReclamation, 'ACCEPTEE').subscribe({
      next: (message) => {
        console.log('Succès:', message);
        reclamation.statut = 'ACCEPTEE';
        alert('Statut changé à ACCEPTEE');
      },
      error: () => {
        alert('Erreur lors de la mise à jour du statut');
      }
    });
  }
  
  terminerTraitement(reclamation: Reclamation) {
    const idReclamation = reclamation.idReclamation ?? 0; // Fallback à 0 si undefined
    this.reclamationService.changerStatut(idReclamation, 'TRAITEE').subscribe({
      next: (message) => {
        console.log('Succès:', message);
        reclamation.statut = 'TRAITEE';
        alert('Statut changé à TRAITEE');
      },
      error: () => {
        alert('Erreur lors de la mise à jour du statut');
      }
    });
  }
  
}

