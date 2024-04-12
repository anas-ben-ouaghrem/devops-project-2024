import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailBlocComponent } from './detail-bloc.component';

describe('DetailBlocComponent', () => {
  let component: DetailBlocComponent;
  let fixture: ComponentFixture<DetailBlocComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailBlocComponent]
    });
    fixture = TestBed.createComponent(DetailBlocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
