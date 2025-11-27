package br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces;

import java.util.List;
import java.util.UUID;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.DistribuicaoDto;

public interface DistribuicaoServiceInterface {

   DistribuicaoDto salvar (DistribuicaoDto distribuicaoDto);

   List<DistribuicaoDto> listar();

   void deletarPorId(UUID id);
   
}