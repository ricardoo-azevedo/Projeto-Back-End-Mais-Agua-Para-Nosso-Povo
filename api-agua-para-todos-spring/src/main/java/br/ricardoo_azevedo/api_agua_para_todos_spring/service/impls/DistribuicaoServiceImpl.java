package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.DistribuicaoDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Cisterna;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Distribuicao;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.CisternaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.FamiliaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.DistribuicaoRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.DistribuicaoServiceInterface;

@Service
public class DistribuicaoServiceImpl implements DistribuicaoServiceInterface{

    @Autowired
    private DistribuicaoRepository distribuicaoRepository;

    @Autowired
    private FamiliaRepository familiaRepository;

    @Autowired
    private CisternaRepository cisternaRepository;

    public Distribuicao toEntity (DistribuicaoDto dto){

        Familia familia = familiaRepository.findById(dto.getIdFamilia())
            .orElseThrow(() -> new RuntimeException("Família não encontrada."));

        Cisterna cisterna = cisternaRepository.findById(dto.getIdCisterna())
            .orElseThrow(() -> new RuntimeException("Cisterna não encontrada."));

        return new Distribuicao(
            dto.getDataEntrega(), 
            dto.getQuantidadeLitros(), 
            dto.getPrevisaoProxima(), 
            dto.getObservacoes(),
            familia,
            cisterna
        );
    }

    public DistribuicaoDto toDto (Distribuicao d){
        return new DistribuicaoDto(
            d.getId(), 
            d.getDataEntrega(), 
            d.getQuantidadeLitros(), 
            d.getPrevisaoProxima(), 
            d.getObservacoes(),
            d.getFamilia().getId(),
            d.getCisterna().getId(),
            d.getCriacaoLocalDateTime()
        );
    }

    @Override
    public DistribuicaoDto salvar(DistribuicaoDto distribuicaoDto) {
        Distribuicao distribuicaoSalva = distribuicaoRepository.save(toEntity(distribuicaoDto));
        return toDto(distribuicaoSalva);
    }

    @Override
    public List<DistribuicaoDto> listar() {
        return distribuicaoRepository.findAll()
            .stream().map(this::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
        if (id == null){
            throw new RuntimeException("O id é nulo");
        }
        if (!distribuicaoRepository.existsById(id)){
            throw new RuntimeException("Distribuição nao encontrada");
        }
        distribuicaoRepository.deleteById(id);
    }

}