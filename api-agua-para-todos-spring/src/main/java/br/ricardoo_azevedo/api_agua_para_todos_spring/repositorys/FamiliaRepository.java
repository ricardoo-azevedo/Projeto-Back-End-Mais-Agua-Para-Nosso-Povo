package br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;

@Repository
public interface FamiliaRepository extends JpaRepository<Familia, UUID> {

  // acho q isso foi reduntante e na real nem precisou
/*   List<Familia> findByEndereco(String endereco) */;

  //cheacar isso no model ein
  boolean existsByEndereco(String endereco);

  List<Familia> findByEnderecoContaining(String nome);

}
