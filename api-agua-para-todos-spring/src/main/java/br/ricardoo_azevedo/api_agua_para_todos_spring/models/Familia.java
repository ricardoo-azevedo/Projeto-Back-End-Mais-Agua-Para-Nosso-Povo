package br.ricardoo_azevedo.api_agua_para_todos_spring.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "tb_familia")
public class Familia {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String nis;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private boolean possui_captacao_calhas;

    @OneToMany(mappedBy = "familia", cascade = CascadeType.ALL, orphanRemoval = true, fetch =  FetchType.EAGER)
    private Set<Membro> membros = new HashSet<>();

    public Familia(String nis, String endereco, double latitude, double longitude, boolean possui_captacao_calhas) {
        this.nis = nis;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.possui_captacao_calhas = possui_captacao_calhas;
    }

    public void adicionarMembro(Membro membro) {
        membro.setFamilia(this);
        membros.add(membro);
    }
}