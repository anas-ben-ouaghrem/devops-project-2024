import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EtudiantRoutingModule } from './etudiant-routing.module';
import { ListEtudiantComponent } from '../../views/etudiant/list-etudiant/list-etudiant.component';
import { FormEtudiantComponent } from '../../views/etudiant/form-etudiant/form-etudiant.component';

@NgModule({
  declarations: [ListEtudiantComponent, FormEtudiantComponent],
  imports: [CommonModule, EtudiantRoutingModule],
})
export class EtudiantModule {}
