<<<<<<< HEAD
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideBarAdminComponent } from './side-bar-admin.component';

describe('SideBarAdminComponent', () => {
  let component: SideBarAdminComponent;
  let fixture: ComponentFixture<SideBarAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SideBarAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideBarAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
=======
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

// Définition des clés acceptées pour éviter les erreurs de type
type FilterKeys = 'session' | 'section' | 'niveau' | 'module' | 'notes';

@Component({
  selector: 'app-side-bar-admin',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './side-bar-admin.component.html',
  styleUrl: './side-bar-admin.component.scss',
})
export class SideBarAdminComponent {
selectSemestre(_t30: any) {
throw new Error('Method not implemented.');
}
selectSection(_t38: any) {
throw new Error('Method not implemented.');
}
selectModule(_t54: any) {
throw new Error('Method not implemented.');
}
selectNiveau(_t46: any) {
throw new Error('Method not implemented.');
}
toggleNiveauxEtudiants() {
throw new Error('Method not implemented.');
}
showNiveauxEtudiants: any;
toggleSectionsEtudiants() {
throw new Error('Method not implemented.');
}
showFiltersEtudiants: any;
showSectionsEtudiants: any;
toggleFiltersEtudiants() {
throw new Error('Method not implemented.');
}
showFiltersNotes: any;
showSessions: any;
sessions: any;
showSemestres: any;
semestres: any;
showSectionsNotes: any;
sections: any;
showNiveauxNotes: any;
niveaux: any;
showModules: any;
modules: any;
toggleModules() {
throw new Error('Method not implemented.');
}
toggleNiveauxNotes() {
throw new Error('Method not implemented.');
}
toggleSectionsNotes() {
throw new Error('Method not implemented.');
}
toggleSemestres() {
throw new Error('Method not implemented.');
}
toggleSessions() {
throw new Error('Method not implemented.');
}
toggleFiltersNotes() {
throw new Error('Method not implemented.');
}
  // Gestion des états de visibilité des menus
  showFilters: Record<FilterKeys, boolean> = {
    notes: false,
    session: false,
    section: false,
    niveau: false,
    module: false,
  };

  // Données des filtres
  filters: Record<Exclude<FilterKeys, 'notes'>, string[]> = {
    session: ['Principal', 'Rattrapage'],
    section: ['Informatique', 'Gsil', 'Infotronique', 'Mécatronique'],
    niveau: ['1ère année', '2ème année', '3ème année'],
    module: ['Maths', 'Sécurité', 'Programmation', 'Réseaux'],
  };

  // Bascule l'affichage du menu
  toggleMenu(menu: FilterKeys) {
    this.showFilters[menu] = !this.showFilters[menu];
  }

  // Récupère les éléments d'un filtre de manière sécurisée
  getFilterItems(category: Exclude<FilterKeys, 'notes'>): string[] {
    return this.filters[category] ?? [];
  }

  // Transforme un identifiant (ex: 'session') en titre (ex: 'Session')
  getTitle(key: string): string {
    return key.charAt(0).toUpperCase() + key.slice(1);
  }

  // Action lors de la sélection d'un élément
  selectItem(category: FilterKeys, item: string) {
    console.log(`Sélectionné : ${category} - ${item}`);
  }

  // Récupère les clés valides sauf 'notes'
  getFilterKeys(): Exclude<FilterKeys, 'notes'>[] {
    return ['session', 'section', 'niveau', 'module'];
  }
}
>>>>>>> origin/ouma
