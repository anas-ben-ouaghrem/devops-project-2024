import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UniversiteRoutingModule } from './universite-routing.module';
import { FormUniversiteComponent } from '../../views/universite/form-universite/form-universite.component';
import { ListUniversiteComponent } from '../../views/universite/list-universite/list-universite.component';

@NgModule({
  declarations: [FormUniversiteComponent, ListUniversiteComponent],
  imports: [CommonModule, UniversiteRoutingModule],
})
export class UniversiteModule {}
