package cl.sanos_y_salvos.rabbirMQ.api.controller;

import cl.sanos_y_salvos.rabbirMQ.api.dto.ExchageRequest;
import cl.sanos_y_salvos.rabbirMQ.api.dto.QueueRequest;
import cl.sanos_y_salvos.rabbirMQ.api.dto.BindingRequest;
import cl.sanos_y_salvos.rabbirMQ.api.service.RabbitService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rabbit")
public class RabbitController {
    
    private final RabbitService rabbitService;

    public RabbitController(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @PostMapping("/queues")
    public ResponseEntity<String> createQueue(@Valid @RequestBody QueueRequest request) {
        rabbitService.createQueue(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Cola creada: " + request.getName());
    }

    @DeleteMapping("/queues/{name}")
    public ResponseEntity<String> deleteQueue(@PathVariable String name) {
        rabbitService.deleteQueue(name);
        return ResponseEntity.ok("Cola eliminada: " + name);
    }

    @PostMapping("/exchanges")
    public ResponseEntity<String> createExchange(@Valid @RequestBody ExchageRequest request) {
        rabbitService.createExchange(request.getName(), request.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body("Exchange creado: " + request.getName());
    }

    @DeleteMapping("/exchanges/{name}")
    public ResponseEntity<String> deleteExchange(@PathVariable String name) {
        rabbitService.deleteExchange(name);
        return ResponseEntity.ok("Exchange eliminado: " + name);
    }

    @PostMapping("/bindings")
    public ResponseEntity<String> createBinding(@Valid @RequestBody BindingRequest request) {
        rabbitService.createBinding(request.getQueueName(), request.getExchangeName(), request.getRoutingKey());
        return ResponseEntity.status(HttpStatus.CREATED).body("Binding creado exitosamente");
    }
}