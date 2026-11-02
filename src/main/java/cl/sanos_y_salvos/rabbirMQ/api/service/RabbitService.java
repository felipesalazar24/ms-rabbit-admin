package cl.sanos_y_salvos.rabbirMQ.api.service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    private final AmqpAdmin amqpAdmin;

    public RabbitService(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    public void createQueue(String name) {
        amqpAdmin.declareQueue(new Queue(name, true));
    }

    public void deleteQueue(String name) {
        amqpAdmin.deleteQueue(name);
    }

    public void createExchange(String name, String type) {
        if ("direct".equalsIgnoreCase(type)) {
            amqpAdmin.declareExchange(new DirectExchange(name, true, false));
        } else if ("topic".equalsIgnoreCase(type)) {
            amqpAdmin.declareExchange(new TopicExchange(name, true, false));
        }
    }
    
    public void deleteExchange(String name) {
        amqpAdmin.deleteExchange(name);
    }
}