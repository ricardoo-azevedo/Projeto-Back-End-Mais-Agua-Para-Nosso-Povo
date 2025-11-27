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
public class FamiliaDto {

  public FamiliaDto(String endereco, boolean possui_captacao_calhas, double latitude, double longitude) {
    this.endereco = endereco;
    this.possui_captacao_calhas = possui_captacao_calhas;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID id;

  // em duvida se deveria ser unique ou nao
  @NotBlank(message = "O endereco nao deve ser nulo")
  @Size(min = 5)
  private String endereco;

  @NotNull(message = "Captacao_calhas nao deve ser nulo")
  private boolean possui_captacao_calhas;

  @NotNull(message = "Latitude nao deve ser nulo")
  private double latitude;

  @NotNull(message = "Logitude nao deve ser nulo")
  private double longitude;


}
