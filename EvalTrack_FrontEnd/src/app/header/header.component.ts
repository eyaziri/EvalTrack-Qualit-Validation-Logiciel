import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  isMenuOpen = false;
  student: any = {};
 idRole =0;
  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
  constructor(private etudiantService: EtudiantServiceService) {}
  ngOnInit() {
    const idUserString = localStorage.getItem('idUser');
    const idRoleString = localStorage.getItem('idRole');

    if (idUserString !== null && idRoleString !== null) {
      const idUser = parseInt(idUserString, 10);
       this.idRole = parseInt(idRoleString, 10);

      this.etudiantService.getUserByIdAndRole(idUser, this.idRole).subscribe({
        next: (data: any) => {
          console.log(data);
      
          if (this.idRole === 1) {
          
            this.student = {
              nomAdmin: data.nom,
              email: data.email
            };
          } else {
         
            this.student = {
              idEtudiant: data.idEtudinat,
              nomEtudiant: data.nom,
              cinEtudiant: data.cin,
              niveauEtudiant: data.niveau,
              nomSection: data.section.nomSection
            };
          }
        },
        error: (err) => {
          console.error('Erreur lors de la récupération de l\'utilisateur :', err);
        }
      });
      
        
      
    } else {
      console.error('ID utilisateur ou ID rôle manquant dans le localStorage');
    }
  }
 
}
