import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormReservationComponent } from 'src/app/views/reservation/form-reservation/form-reservation.component';
import { ListReservationComponent } from 'src/app/views/reservation/list-reservation/list-reservation.component';

const routes: Routes = [
  { path: 'form', component: FormReservationComponent },
  { path: 'list', component: ListReservationComponent },
  { path: 'form/:id', component: FormReservationComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservationRoutingModule { }
