import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContentReclamationComponent } from './content-reclamation.component';

describe('ContentReclamationComponent', () => {
  let component: ContentReclamationComponent;
  let fixture: ComponentFixture<ContentReclamationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContentReclamationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContentReclamationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
