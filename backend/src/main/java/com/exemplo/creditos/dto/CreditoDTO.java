package com.exemplo.creditos.dto;

import com.exemplo.creditos.model.Credito;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreditoDTO {
    private Long id;
    private String numeroCredito;
    private String numeroNfse;
    private LocalDate dataConstituicao;
    private BigDecimal valorIssqn;
    private String tipoCredito;
    private boolean simplesNacional;
    private BigDecimal aliquota;
    private BigDecimal valorFaturado;
    private BigDecimal valorDeducao;
    private BigDecimal baseCalculo;

    public CreditoDTO(Credito credito) {
        this.id = credito.getId();
        this.numeroCredito = credito.getNumeroCredito();
        this.numeroNfse = credito.getNumeroNfse();
        this.dataConstituicao = credito.getDataConstituicao();
        this.valorIssqn = credito.getValorIssqn();
        this.tipoCredito = credito.getTipoCredito();
        this.simplesNacional = credito.isSimplesNacional();
        this.aliquota = credito.getAliquota();
        this.valorFaturado = credito.getValorFaturado();
        this.valorDeducao = credito.getValorDeducao();
        this.baseCalculo = credito.getBaseCalculo();
    }
}