package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Cisterna;

@Repository
public interface CisternaRepository extends JpaRepository<Cisterna, UUID> {
    List<Cisterna> findByFamiliaId(UUID idFamilia);
}