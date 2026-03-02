package com.persou.store.transportlayers.http;

import com.persou.store.config.kafka.producer.OrderProducer;
import com.persou.store.transportlayers.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String enviaPedido(@RequestBody OrderDTO orderDTO) {
        orderProducer.sendMessage(orderDTO);
        return "Pedido recebido: " + orderDTO;
    }

}