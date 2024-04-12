import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListFoyerComponent } from './list-foyer.component';

describe('ListFoyerComponent', () => {
  let component: ListFoyerComponent;
  let fixture: ComponentFixture<ListFoyerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListFoyerComponent]
    });
    fixture = TestBed.createComponent(ListFoyerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
