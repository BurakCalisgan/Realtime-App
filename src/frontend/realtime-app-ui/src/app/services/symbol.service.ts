import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SymbolResponse } from '../interfaces/response/symbol-reponse';
import { SymbolUpdateRequest } from '../interfaces/request/symbol-update-request';

@Injectable({
  providedIn: 'root',
})
export class SymbolService {
  apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getSymbols = (): Observable<SymbolResponse[]> =>
    this.http.get<SymbolResponse[]>(`${this.apiUrl}symbol/all-symbols`);

  updateSymbol = (request: SymbolUpdateRequest): Observable<{ message: string }> =>
    this.http.put<{ message: string }>(`${this.apiUrl}symbol/${request.id}`, request);

}
