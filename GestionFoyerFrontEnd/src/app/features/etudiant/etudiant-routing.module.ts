import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormEtudiantComponent } from 'src/app/views/etudiant/form-etudiant/form-etudiant.component';
import { ListEtudiantComponent } from 'src/app/views/etudiant/list-etudiant/list-etudiant.component';

const routes: Routes = [
  { path: 'form', component: FormEtudiantComponent },
  { path: 'list', component: ListEtudiantComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EtudiantRoutingModule { }
