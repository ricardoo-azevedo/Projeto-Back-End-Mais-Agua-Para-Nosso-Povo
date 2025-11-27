package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Table(name = "TB_Distribuicao")
public class Distribuicao {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    public Distribuicao(LocalDate dataEntrega, int quantidadeLitros, LocalDate previsaoProxima, String observacoes){
        this.dataEntrega = dataEntrega;
        this.quantidadeLitros = quantidadeLitros;
        this.previsaoProxima = previsaoProxima;
        this.observacoes = observacoes;

    }

    @Column(nullable = false)
    LocalDate dataEntrega; 
    
    @Column(nullable = false)
    int quantidadeLitros;

    @Column(nullable = false)
    LocalDate previsaoProxima;

    @Column(nullable = true)
    String observacoes;

    @Column(nullable = false, updatable = false)
    LocalDateTime criacaoLocalDateTime;
    
    @PrePersist
    public void persistencia(){
       this.criacaoLocalDateTime = LocalDateTime.now(); 
    }

}