import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { reservation } from 'src/app/models/reservation';
import { ReservationService } from 'src/app/services/reservation/reservation.service';

@Component({
  selector: 'app-form-reservation',
  templateUrl: './form-reservation.component.html',
  styleUrls: ['./form-reservation.component.css']
})
export class FormReservationComponent {
  ress: reservation = new reservation();
  id = 0;
  selectChamber!: number;
  constructor(
    private ResService: ReservationService,
    private router: Router,
    private ac: ActivatedRoute
  ) {}
  ngOnInit() {
    this.id = this.ac.snapshot.params['id'];
    if (this.id !== undefined) {
      this.ResService.getReservationById(this.id).subscribe({
        next: (data) => (this.ress = data as reservation,  console.log(data)),
      });
    }
  }
  add(f: NgForm) {
    
      
    console.log(typeof f.value);
    console.log('function works !');
    if (this.id !== undefined) {
      this.ress.idReservation = this.id;
      this.ResService.updateReservation(this.ress).subscribe({
        next: () => this.ResService
        .affecterChambreAres(this.id, this.selectChamber)
        .subscribe({
          next: () => this.router.navigate(['/reservation/list']),
          error: (error) => {
            console.error('Error adding reservation:', error);
          },
        }),
      });
    } else {
      
      console.log( typeof this.ress.anneeUniversitaire);   
      
      this.ress.estValide = true;
      this.ResService.addReservation(this.ress).subscribe({
        next: () => this.router.navigate(['/reservation/list']),
      });
    
  }
}


  getButtonMessage() {
    console.log(this.id);
    return this.id !== undefined ? 'Update' : 'Add';
  }
  
}
