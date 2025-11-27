package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.DistribuicaoDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Distribuicao;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.DistribuicaoRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.DistribuicaoServiceInterface;

@Service
public class DistribuicaoServiceImpl implements DistribuicaoServiceInterface{


    @Autowired
    private DistribuicaoRepository distribuicaoRepository;


    public Distribuicao toEntity (DistribuicaoDto distribuicaoDto){
        return new Distribuicao(
            distribuicaoDto.getDataEntrega(), 
            distribuicaoDto.getQuantidadeLitros(), 
            distribuicaoDto.getPrevisaoProxima(), 
            distribuicaoDto.getObservacoes());
    }

    public DistribuicaoDto toDto (Distribuicao distribuicao){
        return new DistribuicaoDto(
            distribuicao.getId(), 
            distribuicao.getDataEntrega(), 
            distribuicao.getQuantidadeLitros(), 
            distribuicao.getPrevisaoProxima(), 
            distribuicao.getObservacoes(),
            distribuicao.getCriacaoLocalDateTime());
    }

    @Override
    public DistribuicaoDto salvar(DistribuicaoDto distribuicaoDto) {
        Distribuicao distribuicaoSalva = distribuicaoRepository.save(toEntity(distribuicaoDto));
        return toDto(distribuicaoSalva);
    }

    @Override
    public List<DistribuicaoDto> listar() {
        List<Distribuicao> distribuicoes = distribuicaoRepository.findAll();
        return distribuicoes.stream()
        .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
        if (id == null){
            throw new RuntimeException("O id é nulo");
        } if (!distribuicaoRepository.existsById(id)){
            throw new RuntimeException("Distribuição nao encontrada");
        }
        distribuicaoRepository.deleteById(id);
    }

}