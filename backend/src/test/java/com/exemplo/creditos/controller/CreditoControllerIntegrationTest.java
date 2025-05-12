package com.exemplo.creditos.controller;


import com.exemplo.creditos.config.IntegrationTestContainerConfig;
import com.exemplo.creditos.model.Credito;
import com.exemplo.creditos.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreditoControllerIntegrationTest  extends IntegrationTestContainerConfig {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CreditoRepository creditoRepository;

    @BeforeEach
    void setup() {
        Credito credito = new Credito();
        credito.setNumeroCredito("777777");
        credito.setNumeroNfse("555555");
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(new BigDecimal("100.00"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.00"));
        credito.setValorFaturado(new BigDecimal("1000.00"));
        credito.setValorDeducao(new BigDecimal("200.00"));
        credito.setBaseCalculo(new BigDecimal("800.00"));

        creditoRepository.save(credito);
    }

    @Test
    void getByNumeroCredito_shouldReturnCredito() {
        String url = "http://localhost:" + port + "/api/creditos?numero=777777";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("777777");
        assertThat(response.getBody()).contains("555555");
    }

    @Test
    void getByNumeroNfse_shouldReturnList() {
        String url = "http://localhost:" + port + "/api/creditos?numero=555555";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("777777");
        assertThat(response.getBody()).contains("555555");
    }
}