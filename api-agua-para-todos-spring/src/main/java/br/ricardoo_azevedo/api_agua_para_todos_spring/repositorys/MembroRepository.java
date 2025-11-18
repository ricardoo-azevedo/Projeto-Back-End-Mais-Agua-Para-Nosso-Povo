package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Membro;

@Repository
public interface MembroRepository extends JpaRepository<Membro, UUID> {

    Optional<Membro> findByCpf(String cpf);

    boolean existsByNome(String nome);
    
    List<Membro> findByNomeContaining(String nome);

    boolean existsByCpf(String cpf);

}