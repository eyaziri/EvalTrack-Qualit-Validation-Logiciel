import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageAjoutModuleComponent } from './admin-page-ajout-module.component';

describe('AdminPageAjoutModuleComponent', () => {
  let component: AdminPageAjoutModuleComponent;
  let fixture: ComponentFixture<AdminPageAjoutModuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPageAjoutModuleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPageAjoutModuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
