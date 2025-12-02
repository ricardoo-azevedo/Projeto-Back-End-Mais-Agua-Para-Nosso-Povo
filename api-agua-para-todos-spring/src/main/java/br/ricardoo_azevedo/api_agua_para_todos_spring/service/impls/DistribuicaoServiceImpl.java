package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.time.LocalDate;
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
public DistribuicaoDto salvar(DistribuicaoDto dto) {

    Familia familia = familiaRepository.findById(dto.getIdFamilia())
        .orElseThrow(() -> new RuntimeException("Família não encontrada."));

    Cisterna cisterna = cisternaRepository.findById(dto.getIdCisterna())
        .orElseThrow(() -> new RuntimeException("Cisterna não encontrada."));

    int moradores = familia.getMembros().size();
    if (moradores == 0) {
        throw new RuntimeException("A família não possui membros cadastrados.");
    }

    int consumoPorPessoaPorDia = 30; 
    int consumoDiarioFamilia = moradores * consumoPorPessoaPorDia;

    int litrosDistribuidos = cisterna.getCapacidadeLitros() - cisterna.getNivelAtual();
    if (litrosDistribuidos < 0) litrosDistribuidos = 0;

    dto.setQuantidadeLitros(litrosDistribuidos);

    int diasDuracao = litrosDistribuidos / consumoDiarioFamilia;
    if (litrosDistribuidos % consumoDiarioFamilia != 0) {
        diasDuracao += 1; 
    }
    LocalDate previsao = dto.getDataEntrega().plusDays(diasDuracao);
    dto.setPrevisaoProxima(previsao);

    cisterna.setNivelAtual(cisterna.getNivelAtual() + litrosDistribuidos);
    if (cisterna.getNivelAtual() > cisterna.getCapacidadeLitros()) {
        cisterna.setNivelAtual(cisterna.getCapacidadeLitros());
    }
    cisternaRepository.save(cisterna);

    Distribuicao distribuicaoSalva = distribuicaoRepository.save(toEntity(dto));

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
    Distribuicao distribuicao = distribuicaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Distribuição não encontrada"));

    Cisterna cisterna = distribuicao.getCisterna();
    cisterna.getDistribuicoes().remove(distribuicao);

    cisternaRepository.save(cisterna);
}
   }