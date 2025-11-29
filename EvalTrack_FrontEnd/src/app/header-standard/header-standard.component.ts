import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header-standard',
  imports: [CommonModule],
  templateUrl: './header-standard.component.html',
  styleUrl: './header-standard.component.scss'
})
export class HeaderStandardComponent {
  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }
}
