import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Bloc } from 'src/app/models/bloc';
import { BlocService } from 'src/app/services/bloc/bloc.service';

@Component({
  selector: 'app-form-bloc',
  templateUrl: './form-bloc.component.html',
  styleUrls: ['./form-bloc.component.css'],
})
export class FormBlocComponent implements OnInit {
  bloc: Bloc = new Bloc();

  constructor(
    private blocService: BlocService,
    private router: Router,
    private ac: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.id = this.ac.snapshot.params['id'];
    if (this.id !== undefined) {
      this.blocService.getBlocById(this.id).subscribe({
        next: (data) => (this.bloc = data),
      });
    }
  }

  id = 0;

  onSubmit(form: NgForm) {
    if (form.valid) {
      if (this.id !== undefined) {
        this.blocService.updateBloc(this.bloc).subscribe({
          next: () => this.router.navigate(['/bloc/list']),
        });
      } else {
        this.blocService.addBloc(this.bloc).subscribe({
          next: () => this.router.navigate(['/bloc/list']),
        });
      }
    }
  }

  getButtonMessage() {
    return this.id !== undefined ? 'Update Bloc' : 'Add Bloc';
  }
}
