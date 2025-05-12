import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

export interface CreditoDTO {
  id: number;
  numeroCredito: string;
  numeroNfse: string;
  dataConstituicao: string;
  valorIssqn: number;
  tipoCredito: string;
  simplesNacional: boolean;
  aliquota: number;
  valorFaturado: number;
  valorDeducao: number;
  baseCalculo: number;
}

@Injectable({
  providedIn: 'root'
})
export class CreditosService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  buscarCreditos(documento: string): Observable<CreditoDTO[]> {
    return this.http.get<CreditoDTO[]>(`${this.apiUrl}?numero=${documento}`);
  }
}
