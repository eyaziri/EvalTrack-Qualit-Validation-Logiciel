import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { DropdownModule } from 'primeng/dropdown';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-side-bar-admin',
  standalone: true,
  imports: [CommonModule, RouterModule, DropdownModule, FormsModule],
  templateUrl: './side-bar-admin.component.html',
  styleUrl: './side-bar-admin.component.scss',
})
export class SideBarAdminComponent {
  constructor(private router: Router) {}

  onLogout() {
   
    localStorage.setItem('idRole', '0');
    localStorage.setItem('token', '');

  
    this.router.navigate(['/login']);
  }
}
