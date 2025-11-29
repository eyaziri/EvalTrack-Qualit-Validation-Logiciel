import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtudiantPageReclamationComponent } from './etudiant-page-reclamation.component';

describe('EtudiantPageReclamationComponent', () => {
  let component: EtudiantPageReclamationComponent;
  let fixture: ComponentFixture<EtudiantPageReclamationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EtudiantPageReclamationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EtudiantPageReclamationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
