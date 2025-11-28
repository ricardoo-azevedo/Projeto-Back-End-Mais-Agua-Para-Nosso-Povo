package br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces;

import java.util.List;
import java.util.UUID;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.MembroDto;

public interface MembroServiceInterface {

    MembroDto salvar(MembroDto membroDto);

    MembroDto editarPorId(MembroDto membroDto, UUID id);

    List<MembroDto> listar();

    MembroDto pesquisarPorId(UUID id);

    MembroDto pesquisarPorCpf(String cpf);

    List<MembroDto> pesquisarPorNome(String nome);

    void deletarPorId(UUID id);
}