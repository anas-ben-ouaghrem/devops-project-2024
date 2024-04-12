import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormFoyerComponent } from 'src/app/views/foyer/form-foyer/form-foyer.component';
import { ListFoyerComponent } from 'src/app/views/foyer/list-foyer/list-foyer.component';

const routes: Routes = [
  { path: 'form', component: FormFoyerComponent },
  { path: 'list', component: ListFoyerComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoyerRoutingModule { }
