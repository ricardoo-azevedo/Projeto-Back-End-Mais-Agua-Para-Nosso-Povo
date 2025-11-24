package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Cisterna;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.CisternaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.CisternaServiceInterface;

@Service
public class CisternaServiceImpl implements CisternaServiceInterface{

    @Autowired
    private CisternaRepository cisternaRepository;

    public Cisterna toEntity(CisternaDto cisternaDto){
       return new Cisterna(
        cisternaDto.getCapacidade_litros(),
        cisternaDto.getNivel_atual()); 

    }

    public CisternaDto toDto (Cisterna cisterna){
        return new CisternaDto(
            cisterna.getId(),
            cisterna.getCapacidade_litros(),
            cisterna.getNivel_atual());
       }

    @Override
    public CisternaDto salvar(CisternaDto cisternaDto) {
        Cisterna cistenaSalva = cisternaRepository.save(toEntity(cisternaDto));
        return toDto(cistenaSalva);
    }

    @Override
    public CisternaDto editarPorId(CisternaDto cisternaDto, UUID id) {
        if(cisternaRepository.existsById(id)){
        throw new RuntimeException("O id nao foi encontrado");
        }
        Cisterna resultado = cisternaRepository.findById(id).orElseThrow(() -> new RuntimeException("cisterna nao encontrada"));
        resultado.setId(cisternaDto.getId());
        resultado.setCapacidade_litros(cisternaDto.getCapacidade_litros());
        resultado.setNivel_atual(cisternaDto.getNivel_atual());
        Cisterna cisternaEditada = cisternaRepository.save(resultado);
        return toDto(cisternaEditada);
        }

    @Override
    public CisternaDto pesquisarPorId(UUID id) {
        if (id == null){
            throw new RuntimeException("O id é nulo");
        }
        return cisternaRepository.findById(id)
        .map(this::toDto).orElseThrow(() -> new RuntimeException("achou nao"));
    }

    @Override
    public List<CisternaDto> listar() {
        List<Cisterna> cisternas = cisternaRepository.findAll();
        return cisternas.stream()
        .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
     if (id == null) {
            throw new RuntimeException("O id é nulo");
        }
        if (cisternaRepository.existsById(id) == false) {
            throw new RuntimeException("Familia nao encontrada");
        }
        cisternaRepository.deleteById(id);
    }   
    
}
