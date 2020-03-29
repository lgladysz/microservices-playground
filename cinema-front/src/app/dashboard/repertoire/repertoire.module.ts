import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { RepertoireComponent } from './repertoire.component';
import { REPERTOIRE_ROUTES } from './repertoire.routes';

@NgModule({
  declarations: [RepertoireComponent],
  imports: [
    CommonModule,
    RouterModule.forChild(REPERTOIRE_ROUTES)
  ]
})
export class RepertoireModule {
}
