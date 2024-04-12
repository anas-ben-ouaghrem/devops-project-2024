import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bloc } from 'src/app/models/bloc';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class BlocService {
  apiUrl = environment.ResUrl + 'bloc/';

  constructor(private http: HttpClient) {}

  getBlocs(): Observable<Bloc[]> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.get<Bloc[]>(this.apiUrl + 'get',{headers});
  }

  getBlocById(id: number): Observable<Bloc> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.get<Bloc>(this.apiUrl + 'getblocbyId/' + id,{headers});
  }

  addBloc(bloc: Bloc): Observable<Bloc> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.post<Bloc>(this.apiUrl + 'addBloc', bloc,{headers});
  }

  updateBloc(bloc: Bloc): Observable<Bloc> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.put<Bloc>(this.apiUrl + 'update-bloc', bloc,{headers});
  }

  deleteBloc(id: number): Observable<any> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.delete(this.apiUrl + 'remove-bloc/' + id,{headers});
  }

  archiveBloc(id: number): Observable<any> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.put(this.apiUrl + 'archivebloc/' + id, {headers});
  }

  affecterChambresABloc(nomBloc: string, numChambre: number[]): Observable<Bloc> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.post<Bloc>(this.apiUrl + 'affecte-chambres-bloc/' + nomBloc + '/chambre', numChambre,{headers});
  }

  getNumChambreAffected(id: number): Observable<number> {
    const headers = this.CreateAuthorisationHidden();
    return this.http.get<number>(this.apiUrl+''+id+'/numChambreAffected',{headers});
  }
  exportBlocToPdf(blocId: number): Observable<Blob> {
    const headers = this.CreateAuthorisationHidden();
    const url = this.apiUrl+blocId+'/export/pdf';
    return this.http.get(url, {headers, responseType: 'blob' });
  }

  private CreateAuthorisationHidden(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if(jwtToken){
      return new HttpHeaders().set('Authorization','Bearer' + jwtToken);
    }else{
      return new HttpHeaders();
    }
  }
}
