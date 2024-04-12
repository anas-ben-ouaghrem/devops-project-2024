import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailChambreComponent } from './detail-chambre.component';

describe('DetailChambreComponent', () => {
  let component: DetailChambreComponent;
  let fixture: ComponentFixture<DetailChambreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailChambreComponent]
    });
    fixture = TestBed.createComponent(DetailChambreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
