package com.exemplo.creditos.repository;

import com.exemplo.creditos.config.IntegrationTestContainerConfig;
import com.exemplo.creditos.model.Credito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CreditoRepositoryIntegrationTest extends IntegrationTestContainerConfig {

    @Autowired
    private CreditoRepository creditoRepository;

    @Test
    void shouldSaveAndRetrieveCredito() {
        Credito credito = new Credito();
        credito.setNumeroCredito("999999");
        credito.setNumeroNfse("888888");
        credito.setDataConstituicao(LocalDate.now());
        credito.setValorIssqn(new BigDecimal("100.00"));
        credito.setTipoCredito("ISSQN");
        credito.setSimplesNacional(true);
        credito.setAliquota(new BigDecimal("5.00"));
        credito.setValorFaturado(new BigDecimal("1000.00"));
        credito.setValorDeducao(new BigDecimal("200.00"));
        credito.setBaseCalculo(new BigDecimal("800.00"));

        creditoRepository.save(credito);

        List<Credito> found = creditoRepository.findByNumeroCreditoOrNumeroNfse("888888");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getNumeroCredito()).isEqualTo("999999");
    }
}