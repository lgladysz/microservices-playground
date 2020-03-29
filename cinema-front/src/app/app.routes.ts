import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { appRoutesPaths } from './app.routes.paths';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegistrationComponent } from './components/registration/registration.component';

export const APP_ROUTES: Routes = [
  {
    path: appRoutesPaths.LOGIN,
    component: LoginComponent
  },
  {
    path: appRoutesPaths.REGISTRATION,
    component: RegistrationComponent
  },
  {
    path: appRoutesPaths.DASHBOARD,
    loadChildren: './dashboard/dashboard.module#DashboardModule'
  },
  {
    path: appRoutesPaths.OTHERS,
    component: PageNotFoundComponent
  }
];
