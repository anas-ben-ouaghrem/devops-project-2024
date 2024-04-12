import { Component } from '@angular/core';
import { ReservationService } from 'src/app/services/reservation/reservation.service';
import { SearchResultsService } from 'src/app/services/reservation/search-results.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {


  constructor(private reservationService: ReservationService, private searchResultsService: SearchResultsService) { }

  performSearch(event: Event) {
    // Cast event.target to HTMLInputElement to access the value property
    const target = event.target as HTMLInputElement;

    // Check if target is not null and not undefined before using the value
    if (target && target.value !== null && target.value !== undefined) {
      // Your search logic here
      const searchInputValue = target.value;
      console.log(searchInputValue);
      if(searchInputValue == "true"  || searchInputValue == "false")
      {
        this.reservationService.searchReservations(searchInputValue,undefined)
      .subscribe(reservations => {
        // Handle the returned reservations as needed
        console.log(reservations);
        this.searchResultsService.updateSearchResults(reservations);
        // Update your component state or perform other actions with the search results
      });
      }
      

    }
  }

 
}
