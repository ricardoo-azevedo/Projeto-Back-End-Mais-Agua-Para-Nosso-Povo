package br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces;

import java.util.List;
import java.util.UUID;
import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;

public interface CisternaServiceInterface {

    CisternaDto salvar(CisternaDto dto);

    List<CisternaDto> listar();

    CisternaDto buscarPorId(UUID id);

    CisternaDto atualizar(UUID id, CisternaDto dto);

    void deletarPorId(UUID id);

    List<CisternaDto> listarPorFamilia(UUID idFamilia);
}