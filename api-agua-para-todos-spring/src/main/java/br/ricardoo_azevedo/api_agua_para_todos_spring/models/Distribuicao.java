package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Table(name = "TB_Distribuicao")
public class Distribuicao {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private LocalDate dataEntrega; 
    
    @Column(nullable = false)
    private int quantidadeLitros;

    @Column(nullable = false)
    private LocalDate previsaoProxima;

    @Column(nullable = true)
    private String observacoes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criacaoLocalDateTime;
    
    @PrePersist
    public void persistencia(){
       this.criacaoLocalDateTime = LocalDateTime.now(); 
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_familia", nullable = false)
    private Familia familia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cisterna", nullable = false)
    private Cisterna cisterna;

    public Distribuicao(LocalDate dataEntrega,
                        int quantidadeLitros,
                        LocalDate previsaoProxima,
                        String observacoes,
                        Familia familia,
                        Cisterna cisterna) {

        this.dataEntrega = dataEntrega;
        this.quantidadeLitros = quantidadeLitros;
        this.previsaoProxima = previsaoProxima;
        this.observacoes = observacoes;
        this.familia = familia;
        this.cisterna = cisterna;
    }
}