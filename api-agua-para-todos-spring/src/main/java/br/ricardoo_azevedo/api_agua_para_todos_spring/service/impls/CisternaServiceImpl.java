package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.CisternaServiceInterface;

public class CisternaServiceImpl implements CisternaServiceInterface{

    @Override
    public CisternaDto salvar(CisternaDto cisternaDto) {
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public CisternaDto editarPorId(CisternaDto cisternaDto, UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarPorId'");
    }

    @Override
    public CisternaDto pesquisarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarPorId'");
    }

    @Override
    public List<CisternaDto> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public void deletarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarPorId'");
    }

}