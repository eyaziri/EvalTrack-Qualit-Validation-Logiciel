import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulaireNoteComponent } from './formulaire-note.component';

describe('FormulaireNoteComponent', () => {
  let component: FormulaireNoteComponent;
  let fixture: ComponentFixture<FormulaireNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormulaireNoteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormulaireNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
