package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.DistribuicaoDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.DistribuicaoRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.DistribuicaoServiceInterface;

@Service
public class DistribuicaoServiceImpl implements DistribuicaoServiceInterface{

    @Autowired
    private DistribuicaoRepository distribuicaoRepository;

    @Override
    public DistribuicaoDto salvar(DistribuicaoDto distribuicaoDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvar'");
    }

    @Override
    public List<DistribuicaoDto> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public void deletarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletarPorId'");
    }

}