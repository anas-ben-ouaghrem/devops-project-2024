import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Bloc } from 'src/app/models/bloc';
import { BlocService } from 'src/app/services/bloc/bloc.service';

@Component({
  selector: 'app-detail-bloc',
  templateUrl: './detail-bloc.component.html',
  styleUrls: ['./detail-bloc.component.css'],
})
export class DetailBlocComponent {
  bloc!: Bloc;

  constructor(
    private route: ActivatedRoute,
    private blocService: BlocService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = +params['id'];
      this.blocService.getBlocById(id).subscribe(
        (bloc: Bloc) => {
          this.bloc = bloc;
        },
        (error) => {
          console.error('Error fetching bloc details:', error);
        }
      );
    });
  }
}
