package com.exemplo.creditos.service;

import com.exemplo.creditos.config.IntegrationTestContainerConfig;
import com.exemplo.creditos.messaging.CreditoEventPublisher;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreditoKafkaPublishTest extends IntegrationTestContainerConfig {

    @Autowired
    private CreditoEventPublisher publisher;

    private Consumer<String, String> consumer;

    @BeforeEach
    void setupConsumer() {
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafka().getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("consulta-creditos"));
    }

    @Test
    void shouldPublishMessageToKafka() {
        // Act - Disparar um evento
        publisher.publishConsultaEvent("NFS-e", "12345");

        // Await - Buscar a mensagem no Kafka (espera até 5 segundos)
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));

        // Assert
        assertThat(records.count()).isGreaterThan(0);
        String message = records.iterator().next().value();

        assertThat(message).contains("consulta_credito");
        assertThat(message).contains("NFS-e");
        assertThat(message).contains("12345");
    }
}
