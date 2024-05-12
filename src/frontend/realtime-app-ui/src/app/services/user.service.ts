import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { jwtDecode } from 'jwt-decode';
import { UserDetail } from '../interfaces/user-detail';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  authService = inject(AuthService);
  apiUrl: string = environment.apiUrl;

  constructor(private http: HttpClient) {}


  getUserDetail = () => {
    const token = this.authService.getToken();
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

}
