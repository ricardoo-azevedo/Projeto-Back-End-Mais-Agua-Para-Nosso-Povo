package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Table(name = "TB_Cisterna")
public class Cisterna {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private int capacidadeLitros;

    @Column(nullable = true)
    private int nivelAtual;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_familia", nullable = false)
    private Familia familia;
    
    @OneToMany(mappedBy = "cisterna", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Distribuicao> distribuicoes = new HashSet<>();

    public Cisterna(int capacidadeLitros, int nivelAtual, Familia familia) {
        this.capacidadeLitros = capacidadeLitros;
        this.nivelAtual = nivelAtual;
        this.familia = familia;
    }

}