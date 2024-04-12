import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Bloc } from 'src/app/models/bloc';
import { Chambre } from 'src/app/models/chambre';
import { ChambreService } from 'src/app/services/chambre/chambre.service';

enum ChambreType {
  SIMPLE = 'SIMPLE',
  DOUBLE = 'DOUBLE',
  TRIPLE = 'TRIPLE',
}

@Component({
  selector: 'app-list-chambre',
  templateUrl: './list-chambre.component.html',
  styleUrls: ['./list-chambre.component.css'],
})
export class ListChambreComponent {
  chambres: Chambre[] = [];
  chambre: Chambre = new Chambre();

  chambreTypes = Object.keys(ChambreType);
  chambreType = ChambreType;

  blocs: Bloc[] = [];

  constructor(private chambreService: ChambreService, private router: Router) {}

  ngOnInit(): void {
    this.fetchChambres();
  }

  deleteChambre(id: number): void {
    this.chambreService.deleteChambre(id).subscribe(
      () => {
        this.fetchChambres();
      },
      (error) => {
        console.error('Error deleting chambre:', error);
      }
    );
  }

  fetchChambres(): void {
    this.chambreService.getChambres().subscribe(
      (data: Chambre[]) => {
        this.chambres = data;
      },
      (error) => {
        console.error('Error fetching chambres:', error);
      }
    );
  }

  viewChambre(id: number): void {
    this.chambreService.getChambreById(id).subscribe(
      (chambre: Chambre) => {
        this.router.navigate(['/chambre/detail', chambre.idChambre]);
      },
      (error) => {
        console.error('Error fetching chambre details:', error);
      }
    );
  }

  updateChambre(id: number): void {
    this.router.navigate(['/chambre/update', id]);
  }

  exportChambre(): void {
    this.chambreService.exportChambreToExcel().subscribe(
      (response) => {
        const url = window.URL.createObjectURL(response);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'chambre-data.xlsx';
        a.click();
        window.URL.revokeObjectURL(url);
      },
      (error: any) => {
        console.error('Error:', error);
      }
    );
  }

  onSortChambreByType(): void {
    this.chambreService.getSortedChambresByType().subscribe(
      (sortedChambres: Chambre[]) => {
        this.chambres = sortedChambres;
        this.fetchChambres;
      },
      (error) => {
        console.error('Error sorting chambres:', error);
      }
    );
  }

  onSortChambreByBloc(): void {
    this.chambreService.getSortedChambresByBloc().subscribe(
      (sortedChambres: Chambre[]) => {
        this.chambres = sortedChambres;
        this.fetchChambres;
      },
      (error) => {
        console.error('Error sorting chambres by bloc :', error);
      }
    );
  }

  
}
