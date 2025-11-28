package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, UUID> {

    boolean existsByNis(String nis);

    Optional<Familia> findByNis(String nis);
}