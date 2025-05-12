package com.exemplo.creditos.messaging;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditoEventPublisherTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private CreditoEventPublisher publisher;

    @Test
    void shouldSendMessageToKafka() {
        publisher.publishConsultaEvent("NFS-e", "999");

        verify(kafkaTemplate).send(eq("consulta-creditos"), anyString());
    }
}
