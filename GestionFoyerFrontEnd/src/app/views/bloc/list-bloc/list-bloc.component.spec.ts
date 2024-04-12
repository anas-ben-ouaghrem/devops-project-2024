import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBlocComponent } from './list-bloc.component';

describe('ListBlocComponent', () => {
  let component: ListBlocComponent;
  let fixture: ComponentFixture<ListBlocComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListBlocComponent]
    });
    fixture = TestBed.createComponent(ListBlocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
