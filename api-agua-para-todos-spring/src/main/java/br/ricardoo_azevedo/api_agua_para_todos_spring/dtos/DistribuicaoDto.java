package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private UUID id;

    @NotNull(message = "Data entrega nao deve ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrega; 
   
    @NotNull(message = "Quantidade de litros nao deve ser nulo")
    private int quantidadeLitros;

    @NotNull(message = "Previsao proxima nao deve ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate previsaoProxima;
   
    @Size(max = 500, message = "Numero maximo de caracteres")
    private String observacoes;

    @NotNull(message = "id da familia nao pode ser nulo")
    private UUID idFamilia;

    @NotNull(message = "id da cisterna nao pode ser nulo")
    private UUID idCisterna;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime criacaoLocalDateTime;

    public DistribuicaoDto(LocalDate dataEntrega, int quantidadeLitros, LocalDate previsaoProxima, String observacoes){
        this.dataEntrega = dataEntrega;
        this.quantidadeLitros = quantidadeLitros;
        this.previsaoProxima = previsaoProxima;
        this.observacoes = observacoes;
    }
}