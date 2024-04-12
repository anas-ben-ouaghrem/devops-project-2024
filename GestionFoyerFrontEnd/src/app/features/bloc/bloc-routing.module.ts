import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailBlocComponent } from 'src/app/views/bloc/detail-bloc/detail-bloc.component';
import { FormBlocComponent } from 'src/app/views/bloc/form-bloc/form-bloc.component';
import { ListBlocComponent } from 'src/app/views/bloc/list-bloc/list-bloc.component';

const routes: Routes = [
  { path: 'form', component: FormBlocComponent },
  { path: 'update/:id', component: FormBlocComponent },
  { path: 'list', component: ListBlocComponent },
  { path: 'detail/:id', component: DetailBlocComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlocRoutingModule { }
