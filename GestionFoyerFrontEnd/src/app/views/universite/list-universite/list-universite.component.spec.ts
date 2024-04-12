import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListUniversiteComponent } from './list-universite.component';

describe('ListUniversiteComponent', () => {
  let component: ListUniversiteComponent;
  let fixture: ComponentFixture<ListUniversiteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListUniversiteComponent]
    });
    fixture = TestBed.createComponent(ListUniversiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
