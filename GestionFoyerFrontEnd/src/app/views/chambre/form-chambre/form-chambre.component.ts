import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Bloc } from 'src/app/models/bloc';
import { Chambre } from 'src/app/models/chambre';
import { BlocService } from 'src/app/services/bloc/bloc.service';
import { ChambreService } from 'src/app/services/chambre/chambre.service';

enum ChambreType {
  SIMPLE = 'SIMPLE',
  DOUBLE = 'DOUBLE',
  TRIPLE = 'TRIPLE',
}

@Component({
  selector: 'app-form-chambre',
  templateUrl: './form-chambre.component.html',
  styleUrls: ['./form-chambre.component.css'],
})
export class FormChambreComponent {
  chambre: Chambre = new Chambre();

  chambreTypes = Object.keys(ChambreType);
  chambreType = ChambreType;

  blocs: Bloc[] = [];

  constructor(
    private blocService: BlocService,
    private chambreService: ChambreService,
    private router: Router,
    private ac: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.ac.snapshot.params['id'];
    if (this.id !== undefined) {
      this.chambreService.getChambreById(this.id).subscribe({
        next: (data) => (this.chambre = data),
      });
    }

    this.blocService.getBlocs().subscribe(
      (data: Bloc[]) => {
        this.blocs = data;
      },
      (error) => {
        console.error('Error fetching blocs:', error);
      }
    );
  }

  id = 0;
  selectedBloc!: string;
  onSubmit(form: NgForm) {
    if (form.valid && this.selectedBloc != null) {
      if (this.id !== undefined && this.id !== 0) {
        console.log(this.chambre.numeroChambre);
        this.chambreService.updateChambre(this.chambre).subscribe({
          next: () =>
            this.blocService
              .affecterChambresABloc(this.selectedBloc, [this.chambre.numeroChambre])
              .subscribe({
                next: () => this.router.navigate(['/chambre/list']),
                error: (error) => {
                  console.error('Error adding chambre:', error);
                },
              }),
          error: (error) => {
            console.error('Error updating chambre:', error);
          },
        });
      } else {
        this.chambreService.addChambre(this.chambre).subscribe({
          next: () =>
            this.blocService
              .affecterChambresABloc(this.selectedBloc, [this.chambre.numeroChambre])
              .subscribe({
                next: () => this.router.navigate(['/chambre/list']),
                error: () => {
                  console.error('Error adding chambre:', );
                },
              }),
          error: (error) => {
            console.error('Error adding chambre:', error);
          },
        });
      }
    } else {
      if (this.id !== undefined && this.id !== 0) {
        this.chambreService.updateChambre(this.chambre).subscribe({
          next: () => this.router.navigate(['/chambre/list']),
          error: (error) => {
            console.error('Error adding chambre:', error);
          },
        });
      } else {
        this.chambreService.addChambre(this.chambre).subscribe({
          next: () => this.router.navigate(['/chambre/list']),
          error: (error) => {
            console.error('Error adding chambre:', error);
          },
        });
      };
    }
  }

  getButtonMessage() {
    return this.id !== undefined && this.id !== 0
      ? 'Update Chambre'
      : 'Add Chambre';
  }
}
