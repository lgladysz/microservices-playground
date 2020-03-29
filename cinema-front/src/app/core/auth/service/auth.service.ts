import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthKeys } from '../enums/auth-keys.enum';
import { Authority } from '../../model/authority.enum';
import { Router } from '@angular/router';
import { appRoutesPaths } from '../../../app.routes.paths';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private jwtHelper = new JwtHelperService();

  constructor(private router: Router) {
  }

  public getToken(): string {
    return localStorage.getItem(AuthKeys.TOKEN);
  }

  public isAuthenticated(): boolean {
    return !this.jwtHelper.isTokenExpired(this.getToken());
  }

  public register(email: string, password: string): void {

  }

  public login(email: string, password: string): void {

  }

  public logout(): void {
    this.clear();
    this.router.navigate([appRoutesPaths.LOGIN]);
  }

  public isInRole(role: Authority): boolean {
    if (!this.isAuthenticated()) {
      return false;
    }
    const user = this.jwtHelper.decodeToken(this.getToken());
    return user.role === role;
  }

  private clear(): void {
    localStorage.removeItem(AuthKeys.TOKEN);
  }
}
