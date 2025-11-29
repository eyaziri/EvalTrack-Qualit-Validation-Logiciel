import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtudiantPageConfigurationComponent } from './etudiant-page-configuration.component';

describe('EtudiantPageConfigurationComponent', () => {
  let component: EtudiantPageConfigurationComponent;
  let fixture: ComponentFixture<EtudiantPageConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EtudiantPageConfigurationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EtudiantPageConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
