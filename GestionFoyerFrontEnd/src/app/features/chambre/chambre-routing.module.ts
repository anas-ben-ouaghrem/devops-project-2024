import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailChambreComponent } from 'src/app/views/chambre/detail-chambre/detail-chambre.component';
import { FormChambreComponent } from 'src/app/views/chambre/form-chambre/form-chambre.component';
import { ListChambreComponent } from 'src/app/views/chambre/list-chambre/list-chambre.component';

const routes: Routes = [
  { path: 'form', component: FormChambreComponent },
  { path: 'update/:id', component: FormChambreComponent },
  { path: 'list', component: ListChambreComponent },
  { path: 'detail/:id', component: DetailChambreComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChambreRoutingModule { }
