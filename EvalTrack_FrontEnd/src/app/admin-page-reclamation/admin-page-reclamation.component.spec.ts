import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageReclamationComponent } from './admin-page-reclamation.component';

describe('AdminPageReclamationComponent', () => {
  let component: AdminPageReclamationComponent;
  let fixture: ComponentFixture<AdminPageReclamationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPageReclamationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPageReclamationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
