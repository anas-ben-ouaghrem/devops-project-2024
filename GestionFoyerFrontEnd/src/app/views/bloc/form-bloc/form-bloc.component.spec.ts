import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormBlocComponent } from './form-bloc.component';

describe('FormBlocComponent', () => {
  let component: FormBlocComponent;
  let fixture: ComponentFixture<FormBlocComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormBlocComponent]
    });
    fixture = TestBed.createComponent(FormBlocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
