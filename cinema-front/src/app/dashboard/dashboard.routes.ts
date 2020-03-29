import { Routes } from '@angular/router';
import { dashboardRoutesPaths } from './dashboard.routes.paths';
import { HOME_ROUTES } from './home/home.routes';
import { AuthGuard } from '../core/auth/guard/auth.guard';
import { RoleGuard } from '../core/auth/guard/role.guard';
import { Authority } from '../core/model/authority.enum';


export const DASHBOARD_ROUTES: Routes = [
  {
    path: dashboardRoutesPaths.HOME,
    children: HOME_ROUTES,
    canActivate: [AuthGuard]
  },
  {
    path: dashboardRoutesPaths.REPERTOIRE,
    loadChildren: './repertoire/repertoire.module#RepertoireModule',
    canActivate: [AuthGuard]
  },
  {
    path: dashboardRoutesPaths.TICKETS,
    loadChildren: './tickets/tickets.module#TicketsModule',
    canActivate: [AuthGuard]
  },
  {
    path: dashboardRoutesPaths.USERS,
    loadChildren: './users/users.module#UsersModule',
    data: { role: Authority.ADMIN }, // todo model extract object?
    canActivate: [RoleGuard]
  }
];
