import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChambreRoutingModule } from './chambre-routing.module';
import { ListChambreComponent } from '../../views/chambre/list-chambre/list-chambre.component';
import { FormChambreComponent } from '../../views/chambre/form-chambre/form-chambre.component';
import { DetailChambreComponent } from '../../views/chambre/detail-chambre/detail-chambre.component';
import { FormsModule } from '@angular/forms';
import { ErrorComponent } from 'src/app/includes/error/error.component';


@NgModule({
  declarations: [ListChambreComponent, FormChambreComponent, DetailChambreComponent,ErrorComponent],
  imports: [CommonModule, ChambreRoutingModule,FormsModule],
})
export class ChambreModule {}
