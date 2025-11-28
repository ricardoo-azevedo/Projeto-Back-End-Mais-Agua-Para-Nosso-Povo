package br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces;

import java.util.List;
import java.util.UUID;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.FamiliaDto;

public interface FamiliaServiceInterface {

    FamiliaDto salvar(FamiliaDto familiaDto);

    FamiliaDto editarPorId(FamiliaDto familiaDto, UUID id);

    List<FamiliaDto> listar();

    FamiliaDto pesquisarPorId(UUID id);

    FamiliaDto pesquisarPorNis(String nis);

    void deletarPorId(UUID id);
}