import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauReclamationComponent } from './tableau-reclamation.component';

describe('TableauReclamationComponent', () => {
  let component: TableauReclamationComponent;
  let fixture: ComponentFixture<TableauReclamationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableauReclamationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableauReclamationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
