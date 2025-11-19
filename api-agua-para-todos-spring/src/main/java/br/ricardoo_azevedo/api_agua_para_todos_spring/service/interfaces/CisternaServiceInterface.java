package br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces;

import java.util.List;
import java.util.UUID;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;

public interface CisternaServiceInterface {

    CisternaDto salvar (CisternaDto cisternaDto);

    CisternaDto editarPorId(CisternaDto cisternaDto, UUID id);

    CisternaDto pesquisarPorId(UUID id);

    List<CisternaDto> listar();

    void deletarPorId(UUID id);
    
}