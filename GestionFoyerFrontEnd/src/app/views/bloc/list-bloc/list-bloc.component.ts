import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Bloc } from 'src/app/models/bloc';
import { BlocService } from 'src/app/services/bloc/bloc.service';

@Component({
  selector: 'app-list-bloc',
  templateUrl: './list-bloc.component.html',
  styleUrls: ['./list-bloc.component.css']
})
export class ListBlocComponent implements OnInit{
  blocs: Bloc[] = [];

  constructor(private blocService: BlocService,private router: Router) { }

  ngOnInit(): void {
    this.fetchBlocs();
  }

  fetchBlocs(): void {
    this.blocService.getBlocs()
      .subscribe(
        (data: Bloc[]) => {
          this.blocs = data;
        },
        (error) => {
          console.error('Error fetching blocs:', error);
        }
      );
  }
  deleteBloc(id: number): void {
    this.blocService.deleteBloc(id).subscribe(
        () => {
          this.fetchBlocs();
        },
        (error) => {
          console.error('Error deleting bloc:', error);
        }
      );
  }
  viewBloc(id: number): void {
    this.blocService.getBlocById(id).subscribe(
      (bloc: Bloc) => {
        this.router.navigate(['/bloc/detail', bloc.idBloc]);
      },
      (error) => {
        console.error('Error fetching bloc details:', error);
      }
    );
  }
  updateBloc(id: number): void {
    this.router.navigate(['/bloc/update', id]);
  }
  
}
