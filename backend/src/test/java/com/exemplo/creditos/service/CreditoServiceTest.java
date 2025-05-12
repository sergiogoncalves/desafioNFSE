package com.exemplo.creditos.service;

import com.exemplo.creditos.dto.CreditoDTO;
import com.exemplo.creditos.event.ConsultaCreditoEvent;
import com.exemplo.creditos.model.Credito;
import com.exemplo.creditos.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CreditoService creditoService;

    @Test
    void buscarCreditosPorNumero_shouldReturnListAndPublishEvent() {
        Credito credito = getSampleCredito();
        when(creditoRepository.findByNumeroCreditoOrNumeroNfse("7891011"))
                .thenReturn(List.of(credito));

        List<CreditoDTO> result = creditoService.buscarCreditosPorNumero("7891011");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNumeroCredito()).isEqualTo("123456");
        verify(eventPublisher, times(1)).publishEvent(any(ConsultaCreditoEvent.class));
    }

    @Test
    void buscarCreditosPorNumero_shouldReturnEmptyListAndPublishEvent() {
        when(creditoRepository.findByNumeroCreditoOrNumeroNfse("0000"))
                .thenReturn(List.of());

        List<CreditoDTO> result = creditoService.buscarCreditosPorNumero("0000");

        assertThat(result).isEmpty();
        verify(eventPublisher, times(1)).publishEvent(any(ConsultaCreditoEvent.class));
    }

    private Credito getSampleCredito() {
        Credito credito = new Credito();
        credito.setId(1L);
        credito.setNumeroCredito("123456");
        credito.setNumeroNfse("7891011");
        credito.setDataConstituicao(LocalDate.of(2024, 2, 25));
        credito.setValorIssqn(new BigDecimal("1500.75"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.0"));
        credito.setValorFaturado(new BigDecimal("30000.00"));
        credito.setValorDeducao(new BigDecimal("5000.00"));
        credito.setBaseCalculo(new BigDecimal("25000.00"));
        return credito;
    }
}