import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageListeEtudiantAdminComponent } from './admin-page-liste-etudiant-admin.component';

describe('AdminPageListeEtudiantAdminComponent', () => {
  let component: AdminPageListeEtudiantAdminComponent;
  let fixture: ComponentFixture<AdminPageListeEtudiantAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPageListeEtudiantAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPageListeEtudiantAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
