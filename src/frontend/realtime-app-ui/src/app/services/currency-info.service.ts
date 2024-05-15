import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CurrencyInfoResponse } from '../interfaces/response/currency-info-reponse';

@Injectable({
  providedIn: 'root',
})
export class CurrencyInfoService {
  apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getCurrencies = (): Observable<CurrencyInfoResponse[]> =>
    this.http.get<CurrencyInfoResponse[]>(`${this.apiUrl}currency-info/all-currencies`);

  getCurrencyDetail = (currency: string): Observable<CurrencyInfoResponse> =>
    this.http.get<CurrencyInfoResponse>(`${this.apiUrl}currency-info/${currency}`);

}
