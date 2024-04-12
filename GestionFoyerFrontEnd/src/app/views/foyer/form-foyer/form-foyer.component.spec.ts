import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFoyerComponent } from './form-foyer.component';

describe('FormFoyerComponent', () => {
  let component: FormFoyerComponent;
  let fixture: ComponentFixture<FormFoyerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormFoyerComponent]
    });
    fixture = TestBed.createComponent(FormFoyerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
