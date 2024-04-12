import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ReservationRoutingModule } from './reservation-routing.module';
import { ListReservationComponent } from '../../views/reservation/list-reservation/list-reservation.component';
import { FormReservationComponent } from '../../views/reservation/form-reservation/form-reservation.component';

@NgModule({
  declarations: [ListReservationComponent, FormReservationComponent],
  imports: [CommonModule, ReservationRoutingModule, FormsModule],
})
export class ReservationModule {}
