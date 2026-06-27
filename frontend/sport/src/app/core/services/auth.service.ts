import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { LoginRequest } from '../models/login-request.model';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/auth-response.model';
import { RegisterRequest } from '../models/register-request.model';
import { User } from '../models/user.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API=`${environment.apiUrl}/auth`;
  isLoggedIn=signal<boolean>(this.hasToken());
  constructor(private http:HttpClient) { }
  login(request:LoginRequest):Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.API}/login`,request);
  }
  register(request:RegisterRequest):Observable<User>{
    return this.http.post<User>(`${this.API}/register`,request);
  }
  getCurrentUser():Observable<User>{
    return this.http.get<User>(`${this.API}/me`);
  }
  saveToken(token:string):void{
    localStorage.setItem("token",token);
    this.isLoggedIn.set(true);
  }
  getToken():string|null{
    return localStorage.getItem("token");
  }
  logout():void{
    localStorage.removeItem("token")
    this.isLoggedIn.set(false);
  }
  private hasToken():boolean{
    return !(localStorage.getItem("token"));
  }
}
