import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUniversiteComponent } from './form-universite.component';

describe('FormUniversiteComponent', () => {
  let component: FormUniversiteComponent;
  let fixture: ComponentFixture<FormUniversiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormUniversiteComponent]
    });
    fixture = TestBed.createComponent(FormUniversiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
