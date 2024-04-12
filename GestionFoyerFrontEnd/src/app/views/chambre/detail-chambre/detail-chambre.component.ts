import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Chambre } from 'src/app/models/chambre';
import { ChambreService } from 'src/app/services/chambre/chambre.service';

@Component({
  selector: 'app-detail-chambre',
  templateUrl: './detail-chambre.component.html',
  styleUrls: ['./detail-chambre.component.css']
})
export class DetailChambreComponent implements OnInit{

  chambre!: Chambre;

  constructor(
    private route: ActivatedRoute,
    private chambreService: ChambreService
  ) {}
  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = +params['id'];
      this.chambreService.getChambreById(id).subscribe(
        (chambre: Chambre) => {
          this.chambre = chambre;
        },
        (error) => {
          console.error('Error fetching bloc details:', error);
        }
      );
    });
  }

  
}

