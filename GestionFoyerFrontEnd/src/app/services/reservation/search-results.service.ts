import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { reservation } from 'src/app/models/reservation';

@Injectable({
  providedIn: 'root'
})
export class SearchResultsService {
  private searchResultsSubject = new BehaviorSubject<reservation[]>([]);
  searchResults$ = this.searchResultsSubject.asObservable();

  updateSearchResults(results: reservation[]) {
    this.searchResultsSubject.next(results);
  }
}