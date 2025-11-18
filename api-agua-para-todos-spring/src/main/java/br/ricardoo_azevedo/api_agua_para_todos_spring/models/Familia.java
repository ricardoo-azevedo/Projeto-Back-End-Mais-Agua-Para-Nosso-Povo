package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

  // em duvida se deveria ser unique ou nao
  @Column(nullable = false)
  private String endereco;

  @Column(nullable = false)
  private boolean possui_captacao_calhas;

  @Column(nullable = false)
  private LocalDate entrega;

  @Column(nullable = false)
  private LocalDate proxima_previsao;

  @Column(nullable = false)
  private float latitude;

  @Column(nullable = false)
  private float longitude;

}
