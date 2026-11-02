package cl.sanos_y_salvos.rabbirMQ.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ExchageRequest {
    @NotBlank(message = "El nombre del exchange no puede estar vacío")
    private String name;

    @NotBlank(message = "El tipo de exchange es obligatorio")
    @Pattern(regexp = "^(direct|topic)$", message = "El tipo debe ser 'direct' o 'topic'")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}