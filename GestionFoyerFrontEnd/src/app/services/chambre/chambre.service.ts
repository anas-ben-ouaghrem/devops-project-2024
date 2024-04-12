import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chambre } from 'src/app/models/chambre';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class ChambreService {
  private apiUrl = environment.ResUrl + 'chambre/';

  constructor(private http: HttpClient) {}

  getChambres(): Observable<Chambre[]> {
   // const headers = this.CreateAuthorisationHidden();
    return this.http.get<Chambre[]>(this.apiUrl + 'getallchambre');
  }

  getChambreById(id: number): Observable<Chambre> {
   // const headers = this.CreateAuthorisationHidden();
    return this.http.get<Chambre>(this.apiUrl + 'getchambrebyid/' + id);
  }

  addChambre(chambre: Chambre): Observable<Chambre> {
   // const headers = this.CreateAuthorisationHidden();
    return this.http.post<Chambre>(this.apiUrl + 'ajouterchambre', chambre);
  }

  updateChambre(chambre: Chambre): Observable<Chambre> {
   // const headers = this.CreateAuthorisationHidden();
    return this.http.put<Chambre>(this.apiUrl + 'modifierchambre', chambre);
  }

  deleteChambre(id: number): Observable<any> {
  //  const headers = this.CreateAuthorisationHidden();
    return this.http.delete(this.apiUrl + 'removeChambre/' + id);
  }
  exportChambreToExcel(): Observable<Blob> {
  //  const headers = this.CreateAuthorisationHidden();
    return this.http.get(this.apiUrl + 'exportChambre', {  responseType: 'blob' });
  }

  getSortedChambresByType(): Observable<Chambre[]>  {
   // const headers = this.CreateAuthorisationHidden();
    return this.http.get<Chambre[]>(this.apiUrl+'sortedByType');
  }

  getSortedChambresByBloc(): Observable<Chambre[]> {
    //const headers = this.CreateAuthorisationHidden();
    return this.http.get<Chambre[]>(this.apiUrl+'sortedByBloc');
  }

  /*private CreateAuthorisationHidden(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken){
      return new HttpHeaders().set('Authorization','Bearer' + jwtToken);
    }else{
      return new HttpHeaders();
    }
  }*/
}
