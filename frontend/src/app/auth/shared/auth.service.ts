import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { SignupRequestPayload } from '../signup/signup-request.payload';
import { LoginRequest } from '../login/login-request.payload';
import { LoginResponse } from '../login/login-response.payload';
import { LocalStorageService } from 'ngx-webstorage';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  refreshTokenRequest = {
    refreshToken: this.getRefreshToken(),
    username: this.getUsername()
  }

  constructor(private http: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequest: SignupRequestPayload): Observable<any> {
    return this.http.post('http://localhost:8080/api/auth/signup', signupRequest, { responseType: 'text' });
  }

  login(loginRequest: LoginRequest): Observable<boolean> {
    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/login', loginRequest).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('expireAt', data.expireAt);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('username', data.username);
      return true;
    }));
  }

  refreshToken() {

    console.log('refresh token');

    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/refreshToken',
      this.refreshTokenRequest, { headers: {"Authorization": `Bearer ${this.getJwt()}` }}).pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expireAt');

        this.localStorage.store('authenticationToken', response.authenticationToken);
        this.localStorage.store('expireAt', response.expireAt);
      }));

  }
  
  isLoggedIn(): boolean {
    return this.getJwt() != null;
  }

  getJwt() {
    return this.localStorage.retrieve('authenticationToken');
  }

  getExpirationTime() {
    return this.localStorage.retrieve('expireAt');
  }

  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  getUsername() {
    return this.localStorage.retrieve('username');
  }
  
  logout() {
    this.http.post('http://localhost:8080/api/auth/logout', this.refreshTokenRequest, { responseType: 'text'})
    .subscribe({
      next: (data) => console.log(data),
      error: (error) => console.error(error)
    });

    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('expireAt');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
  }
}
