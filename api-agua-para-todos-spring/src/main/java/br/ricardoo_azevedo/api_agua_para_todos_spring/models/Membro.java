package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.util.UUID;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="tb_membro")
public class Membro {

    @Id
    @GeneratedValue
    @Column(columnDefinition="BINARY(16)")
    private UUID id;

    @Column(nullable=false)
    private String cpf;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private int ano_nascimento;

    @Column(nullable=false)
    private boolean acamado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_familia", nullable=false)
    private Familia familia;

    public Membro(String cpf, String nome, int ano_nascimento, boolean acamado, Familia familia) {
        this.cpf = cpf;
        this.nome = nome;
        this.ano_nascimento = ano_nascimento;
        this.acamado = acamado;
        this.familia = familia;
    }
}