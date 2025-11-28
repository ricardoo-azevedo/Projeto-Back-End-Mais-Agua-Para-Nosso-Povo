package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MembroDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Size(min = 14, max = 14, message = "CPF deve ter 14 caracteres")
    @NotBlank(message = "CPF obrigatorio")
    private String cpf;

    @NotBlank(message = "Nome obrigatório")
    @Size(min = 5, max = 50)
    private String nome;

    @NotNull(message = "Ano nascimento obrigatório")
    private Integer ano_nascimento;

    @NotNull(message = "Status acamado obrigatório")
    private Boolean acamado;

    @NotNull(message = "Família obrigatória")
    private UUID id_familia;
}