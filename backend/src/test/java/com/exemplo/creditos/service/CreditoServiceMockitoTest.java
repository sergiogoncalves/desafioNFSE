package com.exemplo.creditos.service;

import com.exemplo.creditos.dto.CreditoDTO;
import com.exemplo.creditos.messaging.CreditoEventPublisher;
import com.exemplo.creditos.model.Credito;
import com.exemplo.creditos.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceMockitoTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private CreditoEventPublisher eventPublisher;

    @InjectMocks
    private CreditoService creditoService;

    @Test
    void shouldCallPublisherAndFindByNumeroNfse() {
        Credito credito = getSampleCredito();
        when(creditoRepository.findByNumeroCreditoOrNumeroNfse("123")).thenReturn(List.of(credito));

        var result = creditoService.buscarCreditosPorNumero("123");

        assertThat(result).hasSize(1);
        verify(eventPublisher).publishConsultaEvent("NFS-e", "123");
    }

    @Test
    void shouldCallPublisherAndFindByNumeroCredito() {
        Credito credito = getSampleCredito();
        when(creditoRepository.findByNumeroCreditoOrNumeroNfse("ABC")).thenReturn(List.of(credito));

        var result = creditoService.buscarCreditosPorNumero("ABC");

        assertThat(result).hasSize(1);
        verify(eventPublisher).publishConsultaEvent("NumeroCredito", "ABC");
    }

    private Credito getSampleCredito() {
        Credito credito = new Credito();
        credito.setNumeroCredito("ABC");
        credito.setNumeroNfse("123");
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(new BigDecimal("100.00"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.00"));
        credito.setValorFaturado(new BigDecimal("1000.00"));
        credito.setValorDeducao(new BigDecimal("200.00"));
        credito.setBaseCalculo(new BigDecimal("800.00"));
        return credito;
    }
}