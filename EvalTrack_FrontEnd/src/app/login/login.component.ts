import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
 
  constructor(private fb: FormBuilder, private router: Router, private etudiantService :EtudiantServiceService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['student', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const role = this.loginForm.get('role')?.value;
      if (role === 'student') {
        this.router.navigate(['/etudiant-page-accueil']);
      } else if (role === 'admin') {
        this.router.navigate(['/admin-acceuil']);
      } else {
        alert('Rôle non valide');
      }
    }
  }



  onLogin() {
    const student = {
      email: this.loginForm.get('email')?.value,
      motDePasse: this.loginForm.get('password')?.value
    };
   
  
    this.etudiantService.LoginStudent(student).subscribe({
      next: (response) => {
      
  
        if (response && response.token && response.idRole) {
          localStorage.setItem('token', response.token);
          localStorage.setItem('idRole', response.idRole);
          localStorage.setItem('idUser', response.idUser);
  
          if (response.idRole === 1) {
            this.router.navigate(['/admin/notes']);
          } else if (response.idRole === 2) {
          
            this.router.navigate(['/etudiant-page-dashboard']);
          } else {
            alert('Rôle utilisateur non reconnu.');
          }
        } else {
          alert('Réponse invalide du serveur.');
        }
      },
      error: (error) => {
      }
    });
  }
  
}
