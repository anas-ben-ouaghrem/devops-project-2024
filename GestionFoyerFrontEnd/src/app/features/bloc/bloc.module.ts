import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlocRoutingModule } from './bloc-routing.module';
import { FormBlocComponent } from '../../views/bloc/form-bloc/form-bloc.component';
import { ListBlocComponent } from '../../views/bloc/list-bloc/list-bloc.component';
import { FormsModule } from '@angular/forms';
import { DetailBlocComponent } from '../../views/bloc/detail-bloc/detail-bloc.component';

@NgModule({
  declarations: [FormBlocComponent, ListBlocComponent, DetailBlocComponent],
  imports: [CommonModule, BlocRoutingModule,FormsModule],
})
export class BlocModule {}
