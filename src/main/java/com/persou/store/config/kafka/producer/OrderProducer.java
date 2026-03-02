package com.persou.store.config.kafka.producer;

import com.persou.store.transportlayers.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    private static final String TOPIC = "pedido-simples";

    public OrderProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderDTO orderDTO) {
        log.info("Pedido recebido: {}", orderDTO.id());

        // Envia a mensagem e guarda a "promessa" de entrega (CompletableFuture)
        CompletableFuture<SendResult<String, OrderDTO>> future =
            kafkaTemplate.send(TOPIC, orderDTO);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Mensagem enviada com sucesso: {}", orderDTO.id());
                log.info("Tópico: {}", result.getRecordMetadata().topic());
                log.info("Partição: {}", result.getRecordMetadata().partition());
                log.info("Offset: {}", result.getRecordMetadata().offset());
                log.info("Timestamp: {}", result.getRecordMetadata().timestamp());
            } else {
                log.error("Erro ao enviar mensagem: {}", ex.getMessage(), ex);
            }
        });
        log.info("Mensagem entregue ao Kafka para processamento assíncrono");
    }

}