package com.exemplo.creditos.controller;

import com.exemplo.creditos.dto.CreditoDTO;
import com.exemplo.creditos.service.CreditoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Test
    void shouldReturnCreditoList() throws Exception {
        CreditoDTO dto = getSampleDTO();
        when(creditoService.buscarCreditosPorNumero("555")).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/creditos?numero=555"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("12345")));
    }

    @Test
    void shouldReturnNotFoundForNumeroCredito() throws Exception {
        when(creditoService.buscarCreditosPorNumero("999")).thenReturn(List.of());

        mockMvc.perform(get("/api/creditos?numero=999"))
                .andExpect(status().isNotFound());
    }

    private CreditoDTO getSampleDTO() {
        CreditoDTO dto = new CreditoDTO();
        dto.setNumeroCredito("12345");
        dto.setNumeroNfse("555");
        dto.setDataConstituicao(LocalDate.now());
        dto.setValorIssqn(new BigDecimal("100.00"));
        dto.setTipoCredito("ISSQN");
        dto.setSimplesNacional(true);
        dto.setAliquota(new BigDecimal("5.00"));
        dto.setValorFaturado(new BigDecimal("1000.00"));
        dto.setValorDeducao(new BigDecimal("200.00"));
        dto.setBaseCalculo(new BigDecimal("800.00"));
        return dto;
    }
}