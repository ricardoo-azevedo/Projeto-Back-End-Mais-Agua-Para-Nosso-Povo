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
@Getter @Setter 
@NoArgsConstructor 
@AllArgsConstructor
@ToString
@Table(name ="tb_membro")
public class Membro {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    UUID id;

    public Membro (String cpf, String nome, int ano_nascimento, boolean acamado){
        this.cpf = cpf; 
        this.nome = nome;
        this.ano_nascimento = ano_nascimento;
        this.acamado = acamado;
    }

    @Column(nullable = false)
    String cpf;

    @Column(nullable = false)
    String nome;

    @Column(nullable = false)
    int ano_nascimento;

    @Column(nullable = false)
    boolean acamado;

}