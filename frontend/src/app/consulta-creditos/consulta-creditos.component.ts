import { Component } from '@angular/core';
import {CreditoDTO, CreditosService} from "../service/creditos.service";


@Component({
  selector: 'app-consulta-creditos',
  templateUrl: './consulta-creditos.component.html',
  styleUrls: ['./consulta-creditos.component.scss']
})
export class ConsultaCreditosComponent {
  documento: string = '';
  creditos: CreditoDTO[] = [];
  displayedColumns: string[] = [
    'id',
    'numeroCredito',
    'numeroNfse',
    'dataConstituicao',
    'valorIssqn',
    'tipoCredito',
    'simplesNacional',
    'aliquota',
    'valorFaturado',
    'valorDeducao',
    'baseCalculo'
  ];

  constructor(private creditosService: CreditosService) { }

  buscarCreditos(): void {
    if (this.documento.trim()) {
      this.creditosService.buscarCreditos(this.documento).subscribe({
        next: (data: CreditoDTO[]) => this.creditos = data,
        error: (err: any) => {
          console.error('Erro ao buscar créditos:', err);
          this.creditos = [];
        }
      });
    }
  }
}
