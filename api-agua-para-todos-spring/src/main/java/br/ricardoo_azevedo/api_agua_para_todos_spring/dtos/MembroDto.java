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

    public MembroDto (String cpf, String nome, int ano_nascimento, boolean acamado){
        this.cpf = cpf; 
        this.nome = nome;
        this.ano_nascimento = ano_nascimento;
        this.acamado = acamado;
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UUID id;

    @Size(min = 14, max = 14, message = "o cpf deve ser completo")
    @NotBlank(message = "Nao deve ser nulo")
    String cpf;

    @NotBlank(message = "O nome nao deve ser nulo")
    @Size(min =5, max = 50)
    String nome;

    @NotNull(message = "Ano nascimento nao deve ser nulo")
    int ano_nascimento;

    @NotNull(message = "acamdo nao deve ser nulo")
    boolean acamado;


}