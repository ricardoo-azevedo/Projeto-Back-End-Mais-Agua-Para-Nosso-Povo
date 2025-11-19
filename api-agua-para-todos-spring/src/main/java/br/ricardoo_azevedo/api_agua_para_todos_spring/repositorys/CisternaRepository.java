package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Cisterna;

public interface CisternaRepository extends JpaRepository<Cisterna, UUID> {
    
}