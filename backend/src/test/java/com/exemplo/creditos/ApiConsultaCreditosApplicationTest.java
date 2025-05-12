package com.exemplo.creditos;

import com.exemplo.creditos.config.IntegrationTestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApiConsultaCreditosApplicationTest extends IntegrationTestContainerConfig {

    @Test
    public void contextLoads() {
        // Se o contexto subir sem exceção, o teste passa.
    }
}