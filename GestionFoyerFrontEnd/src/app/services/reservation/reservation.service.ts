import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { reservation } from 'src/app/models/reservation';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {


  apiUrl = environment.ResUrl + 'reservation/';

  constructor(private http: HttpClient) { }

  getReservations(): Observable<reservation[]> {
    return this.http.get<reservation[]>(this.apiUrl + "get");
  }

  getReservationById(id: number): Observable<reservation> {
    return this.http.get<reservation>(this.apiUrl+"getReservationbyId/" + id);
  }

  addReservation(R: reservation): Observable<reservation> {
    return this.http.post<reservation>(this.apiUrl + "addReservation", R);
  }

  updateReservation(R: reservation): Observable<reservation> {
    return this.http.put<reservation>(this.apiUrl+"updatereservation", R);
  }
  affecterChambreAres(idres: number, numChambre: number): Observable<reservation> {
    
    const url = `${this.apiUrl}affecte-chambres-res/${idres}/${numChambre}`;
  
  // The second argument is an empty object as the request body
  return this.http.post<reservation>(url, {});
  }

  deleteReservation(id: number): Observable<any> {
    return this.http.delete(this.apiUrl+"removeRes/" + id);
  }


  searchReservations(estValide: String, anneeUniversitaire?: Date  | undefined): Observable<reservation[]> {
    // Build the URL with query parameters
    let url = this.apiUrl + 'rechercherRes';
    if (estValide !== undefined || anneeUniversitaire !== undefined) {
      url += '?';
      if (estValide !== undefined) {
        url += 'estValide=' + estValide.toString();
        if (anneeUniversitaire !== undefined) {
          url += '&';
        }
      }
      if (anneeUniversitaire !== undefined) {
        url += 'anneeUniversitaire=' + anneeUniversitaire.toISOString();
      }
    }
    console.log(url);    // Make the HTTP request to the Spring Boot endpoint
    return this.http.get<reservation[]>(url);
  }
  getQrCodeImage(content: string): Observable<Blob> {
    const url = `${this.apiUrl}qrcode/${content}`;
    return this.http.get(url, { responseType: 'blob' });
  }
}
