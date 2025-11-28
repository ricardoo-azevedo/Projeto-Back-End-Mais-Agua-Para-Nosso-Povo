package br.ricardoo_azevedo.api_agua_para_todos_spring.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class FamiliaDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotBlank(message = "NIS não pode ser nulo")
    private String nis;

    @NotBlank(message = "Endereço não pode ser nulo")
    private String endereco;

    @NotNull(message = "Latitude não pode ser nulo")
    private double latitude;

    @NotNull(message = "Longitude não pode ser nulo")
    private double longitude;

     @NotNull(message = "Captação calhas não pode ser nulo")
    private boolean possui_captacao_calhas; 

}