import { Routes } from '@angular/router';
import { ConsultaCreditosComponent } from './consulta-creditos/consulta-creditos.component';

export const routes: Routes = [
    { path: '', component: ConsultaCreditosComponent },
    { path: '**', redirectTo: '' }
];
