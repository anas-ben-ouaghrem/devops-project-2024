import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbarComponent } from './includes/navbar/navbar.component';
import { HomeComponent } from './views/home/home.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path: 'bloc',
    loadChildren: () =>
      import('./features/bloc/bloc.module').then(
        (m) => m.BlocModule
      ),
  },
  {
    path: 'chambre',
    loadChildren: () =>
      import('./features/chambre/chambre.module').then(
        (m) => m.ChambreModule
      ),
  },
  {
    path: 'etudiant',
    loadChildren: () =>
      import('./features/etudiant/etudiant.module').then(
        (m) => m.EtudiantModule
      ),
  },
  {
    path: 'foyer',
    loadChildren: () =>
      import('./features/foyer/foyer.module').then(
        (m) => m.FoyerModule
      ),
  },
  {
    path: 'reservation',
    loadChildren: () =>
      import('./features/reservation/reservation.module').then(
        (m) => m.ReservationModule
      ),
  },
  {
    path: 'universite',
    loadChildren: () =>
      import('./features/universite/universite.module').then(
        (m) => m.UniversiteModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
