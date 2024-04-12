import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FoyerRoutingModule } from './foyer-routing.module';
import { FormFoyerComponent } from '../../views/foyer/form-foyer/form-foyer.component';
import { ListFoyerComponent } from '../../views/foyer/list-foyer/list-foyer.component';

@NgModule({
  declarations: [FormFoyerComponent, ListFoyerComponent],
  imports: [CommonModule, FoyerRoutingModule],
})
export class FoyerModule {}
