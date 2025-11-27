package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Distribuicao;

@Repository
public interface DistribuicaoRepository extends JpaRepository<Distribuicao, UUID>{

}