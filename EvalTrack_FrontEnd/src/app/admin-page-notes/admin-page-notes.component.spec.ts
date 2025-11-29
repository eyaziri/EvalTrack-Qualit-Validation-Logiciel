import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageNotesComponent } from './admin-page-notes.component';

describe('AdminPageNotesComponent', () => {
  let component: AdminPageNotesComponent;
  let fixture: ComponentFixture<AdminPageNotesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPageNotesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPageNotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
