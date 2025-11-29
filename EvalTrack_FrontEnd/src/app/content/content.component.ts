import { Component } from '@angular/core';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-content',
  imports: [FormsModule,CommonModule],
  templateUrl: './content.component.html',
  styleUrl: './content.component.scss'
})
export class ContentComponent {
  oldPassword: string = '';
  newPassword: string = '';
  confirmPassword: string = '';
  email: string = '';
  idRole: string = '';
  successMessage: string = '';
  errorMessage: string = '';
 constructor (private etudiantService :EtudiantServiceService, private router: Router )
 {

 }
 ngOnInit() {
  
  this.idRole = localStorage.getItem('idRole') || '';
}

 update()
 {
  this.etudiantService.updatepassword(this.email, this.newPassword, this.oldPassword, this.idRole)
  .subscribe({
    next: (message) => {
      this.successMessage = message;
      this.errorMessage = '';
      localStorage.clear();
      setTimeout(() => {
        this.router.navigate(['/login']);
      }, 1000);
    },
    error: (err) => {
      this.errorMessage = err.message || 'Erreur lors de la mise à jour du mot de passe.';
      this.successMessage = '';
    }
  });
}
 }

