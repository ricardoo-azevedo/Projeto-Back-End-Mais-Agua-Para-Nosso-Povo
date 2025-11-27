package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString @Getter @Setter
public class DistribuicaoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    UUID id;

    public DistribuicaoDto(LocalDate dataEntrega, int quantidadeLitros, LocalDate previsaoProxima, String observacoes){
        this.dataEntrega = dataEntrega;
        this.quantidadeLitros = quantidadeLitros;
        this.previsaoProxima = previsaoProxima;
        this.observacoes = observacoes;

    }

    @NotNull(message = "Data entrega nao deve ser nulo")
    LocalDate dataEntrega; 
   
    @NotNull(message = "Quantidade de litros nao deve ser nulo")
    int quantidadeLitros;

    @NotNull(message = "Previsao proxima nao deve ser nulo")
    LocalDate previsaoProxima;
   
    @Size(max = 500, message = "Numero maximo de caracteres")
    String observacoes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime criacaoLocalDateTime;


}