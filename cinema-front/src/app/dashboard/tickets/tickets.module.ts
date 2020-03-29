import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TicketsComponent } from './tickets.component';
import { RouterModule } from '@angular/router';
import { TICKETS_ROUTES } from './tickets.routes';

@NgModule({
  declarations: [TicketsComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(TICKETS_ROUTES)
  ]
})
export class TicketsModule {
}
