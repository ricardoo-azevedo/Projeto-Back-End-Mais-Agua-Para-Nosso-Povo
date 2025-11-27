package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
@Table(name = "tb_familia")
public class Familia {

  @Id
  @GeneratedValue
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  public Familia(String endereco, boolean possui_captacao_calhas, double latitude, double longitude) {
    this.endereco = endereco;
    this.possui_captacao_calhas = possui_captacao_calhas;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  // em duvida se deveria ser unique ou nao
  @Column(nullable = false)
  private String endereco;

  @Column(nullable = false)
  private boolean possui_captacao_calhas;

  @Column(nullable = false)
  private double latitude;

  @Column(nullable = false)
  private double longitude;

}
