import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderStandardComponent } from './header-standard.component';

describe('HeaderStandardComponent', () => {
  let component: HeaderStandardComponent;
  let fixture: ComponentFixture<HeaderStandardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeaderStandardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderStandardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
