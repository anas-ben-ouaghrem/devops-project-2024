import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormUniversiteComponent } from 'src/app/views/universite/form-universite/form-universite.component';
import { ListUniversiteComponent } from 'src/app/views/universite/list-universite/list-universite.component';

const routes: Routes = [
  { path: 'form', component: FormUniversiteComponent },
  { path: 'list', component: ListUniversiteComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UniversiteRoutingModule { }
