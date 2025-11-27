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
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Table(name ="TB_Cisterna")
public class Cisterna {

    public Cisterna(int capacidade_litros, int nivel_atual) {
        this.capacidade_litros = capacidade_litros;
        this.nivel_atual = nivel_atual;
    }

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private int capacidade_litros;

    @Column(nullable = false)
    private int nivel_atual;

}