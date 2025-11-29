import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPageConfigurationComponent } from './admin-page-configuration.component';

describe('AdminPageConfigurationComponent', () => {
  let component: AdminPageConfigurationComponent;
  let fixture: ComponentFixture<AdminPageConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPageConfigurationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPageConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
