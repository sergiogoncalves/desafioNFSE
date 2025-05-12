package com.exemplo.creditos.messaging;



import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreditoEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String TOPIC = "consulta-creditos";

    public void publishConsultaEvent(String tipoConsulta, String chaveConsulta) {
        try {
            Map<String, Object> event = new HashMap<>();
            event.put("evento", "consulta_credito");
            event.put("tipoConsulta", tipoConsulta);
            event.put("chaveConsulta", chaveConsulta);
            event.put("timestamp", Instant.now().toString());

            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, message);
        } catch (Exception e) {
            // Loga mas não interrompe o fluxo da aplicação
            System.err.println("Erro ao publicar evento no Kafka: " + e.getMessage());
        }
    }
}