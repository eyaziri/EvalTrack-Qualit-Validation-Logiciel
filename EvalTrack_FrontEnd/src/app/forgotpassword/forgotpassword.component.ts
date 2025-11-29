import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { Router } from '@angular/router';
import { EtudiantServiceService } from '../../Services/etudiant-service.service';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './forgotpassword.component.html',
  styleUrls: ['./forgotpassword.component.scss']
})
export class ForgotPasswordComponent {
  forgotPasswordForm: FormGroup;
  verificationForm: FormGroup;
  showVerificationCode = false;
  isSubmitting = false;
  isVerifying = false;
  successMessage: string = '';
errorMessage: string = '';

  constructor(private fb: FormBuilder ,private etudiantService : EtudiantServiceService) {
    // Formulaire d'email
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]
    });

    // Formulaire de vérification
    this.verificationForm = this.fb.group({
      verificationCode: ['', [Validators.required]]
    });
  }

  get verificationCode() {
    return this.verificationForm.get('verificationCode');
  }

  onSubmit() {
    this.etudiantService.sendVerificationCode(this.forgotPasswordForm.get('email')?.value).subscribe({
      next: (response) => {
        console.log('Code sent successfully:', response);
        // You can show a success message to the user here
      },
      error: (err) => {
        console.error('Error sending code:', err);
        // Show error to user if needed
      }
    });
    this.showVerificationCode = true;
  }

  verifyCode() {
    const code = this.verificationForm.get('verificationCode')?.value;
    console.log('Code saisi :', code);
    this.etudiantService.VerifyCode(this.forgotPasswordForm.get('email')?.value,code).subscribe({
      next: (response) => {
        console.log('Code verified successfully:', response);
        this.successMessage = 'Code vérifié avec succès. Veuillez vérifier votre boîte mail.';
        this.errorMessage = '';  
      },
      error: (err) => {
        console.error('Error verifying code:', err);
        this.errorMessage = 'Code invalide ou expiré.';
      this.successMessage = ''; 
      }
    });
  }
}