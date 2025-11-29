import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtudiantPageDashboardComponent } from './etudiant-page-dashboard.component';

describe('EtudiantPageDashboardComponent', () => {
  let component: EtudiantPageDashboardComponent;
  let fixture: ComponentFixture<EtudiantPageDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EtudiantPageDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EtudiantPageDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
