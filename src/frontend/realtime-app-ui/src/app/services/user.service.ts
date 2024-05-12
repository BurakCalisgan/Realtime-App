import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserDetail } from '../interfaces/response/user-detail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  authService = inject(AuthService);
  apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getDetail = (): Observable<UserDetail> =>
    this.http.get<UserDetail>(`${this.apiUrl}user/user-info/${this.authService.getUserDetailFromToken()?.id}`);
}
