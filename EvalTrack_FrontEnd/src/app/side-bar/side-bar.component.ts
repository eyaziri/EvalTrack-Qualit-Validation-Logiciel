import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { RouterOutlet } from '@angular/router';
import { RouterModule, Router } from '@angular/router';
@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive,RouterOutlet,RouterModule],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.scss'
})
export class SideBarComponent {
  constructor(private router: Router) {}
  onLogout() {
   
    localStorage.setItem('idRole', '0');
    localStorage.setItem('token', '');

  
    this.router.navigate(['/login']);
  }

}
