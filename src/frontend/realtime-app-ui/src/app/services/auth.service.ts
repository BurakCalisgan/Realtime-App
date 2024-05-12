import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { LoginRequest } from '../interfaces/request/login-request';
import { Observable, map } from 'rxjs';
import { AuthResponse } from '../interfaces/response/auth-response';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import { RegisterRequest } from '../interfaces/request/register-request';
import { UserDetail } from '../interfaces/response/user-detail';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiUrl: string = environment.apiUrl;
  private tokenKey = 'token';

  constructor(private http: HttpClient) {}

  login(data: LoginRequest): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.apiUrl}auth/login`, data)
      .pipe(
        map((response) => {
          if (response.token) {
            localStorage.setItem(this.tokenKey, response.token);
          }
          return response;
        })
      );
  }

  register(data: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}auth/register`, data);
  }

  forgotPassword = (email: string): Observable<AuthResponse> =>
    this.http.post<AuthResponse>(`${this.apiUrl}account/forgot-password`, {
      email,
    });
  

  getUserDetailFromToken = () => {
    const token = this.getToken();
    if (!token) return null;
    const decodedToken: any = jwtDecode(token);
    const userDetail = {
      id: decodedToken.userId,
      username: decodedToken.sub,
      email: decodedToken.email,
      roles: decodedToken.roles || [],
    };

    return userDetail;
  };

  isLoggedIn = (): boolean => {
    const token = this.getToken();
    if (!token) return false;
    return !this.isTokenExpired();
  };

  private isTokenExpired() {
    const token = this.getToken();
    if (!token) return true;
    const decoded = jwtDecode(token);
    const isTokenExpired = Date.now() >= decoded['exp']! * 1000;
    if (isTokenExpired) this.logout();
    return isTokenExpired;
  }

  getRoles = (): string[] | null => {
    const token = this.getToken();
    if (!token) return null;

    const decodedToken: any = jwtDecode(token);
    return decodedToken.role || null;
  };

  logout = (): void => {
    localStorage.removeItem(this.tokenKey);
  };

  getAll = (): Observable<UserDetail[]> =>
    this.http.get<UserDetail[]>(`${this.apiUrl}account`);

  getToken = (): string | null => localStorage.getItem(this.tokenKey) || '';
}
