package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString @Getter @Setter
public class CisternaDto {

    public CisternaDto (int capacidade_litros, int nivel_atual) {
        this.capacidade_litros = capacidade_litros;
        this.nivel_atual = nivel_atual;
    }

    UUID id;

    @NotNull(message = "Capacidade de litros nao deve ser nulo!")
    int capacidade_litros;

    @NotNull(message = "Nivel atual nao deve ser nulo!")
    int nivel_atual;
    
}