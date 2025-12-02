package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class CisternaDto {


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull(message = "Capacidade não pode ser nula")
    private int capacidadeLitros;

    private int nivelAtual;

    @NotNull(message = "id da familia não pode ser nulo")
    private UUID id_familia;

}