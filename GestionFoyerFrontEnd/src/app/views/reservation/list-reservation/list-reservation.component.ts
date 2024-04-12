import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { reservation } from 'src/app/models/reservation';
import { ReservationService } from 'src/app/services/reservation/reservation.service';
import { SearchResultsService } from 'src/app/services/reservation/search-results.service';



@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.css']
})
export class ListReservationComponent {
  qrCodeContent = ''; // Set the content for the QR code
  qrCodeImage: any;
  showQrCodeImage = false;

  listReservations: reservation[] = [];
  constructor(private _router: Router, private _resService: ReservationService ,private searchResultsService: SearchResultsService) {}

  ngOnInit(): void {
    this._resService.getReservations().subscribe({
      next: (data) => (this.listReservations = data as reservation[],console.log(data)),
      error: (err) => console.log(err),

    });

this.searchResultsService.searchResults$.subscribe(results => {
      this.listReservations = results;
    });
  }
  deleteRes(id: number) {
    this._resService.deleteReservation(id).subscribe({
      next : ()=>this.listReservations = this.listReservations.filter(res=>res.idReservation!== id)
    });
  }

  qrCode(): void {
    
    this.showQrCodeImage = !this.showQrCodeImage;
    if (this.showQrCodeImage) {
      const reservationCodes = this.listReservations.map(res =>
        `ID: ${res.idReservation}, \n Valid: ${res.estValide}, \n Year: ${res.anneeUniversitaire}, \n `,console.log("rffvre")
      );
    
      this.qrCodeContent = reservationCodes.join('\n'); 

      this._resService.getQrCodeImage(this.qrCodeContent).subscribe(
      (data: any) => {
        this.qrCodeImage = URL.createObjectURL(data);
        this.showQrCodeImage = true;
      },
      (error) => {
        console.error('Error fetching QR code image', error);
      }
    );
    }
    
  }
}
