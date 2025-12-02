package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Cisterna;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.CisternaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.FamiliaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.CisternaServiceInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CisternaServiceImpl implements CisternaServiceInterface {

    @Autowired
    private CisternaRepository cisternaRepository;

    @Autowired
    private FamiliaRepository familiaRepository;

    private CisternaDto toDto(Cisterna cisterna) {
        return new CisternaDto(
                cisterna.getId(),
                cisterna.getCapacidadeLitros(),
                cisterna.getNivelAtual(),
                cisterna.getFamilia().getId()
        );
    }

    public Cisterna toEntity (CisternaDto dto){
        Familia familia = familiaRepository.findById(dto.getId_familia())
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));
        return new Cisterna(
                dto.getCapacidadeLitros(),
                dto.getNivelAtual(),
                familia
        );
    }


    @Override
    public CisternaDto salvar(CisternaDto dto) {
        Cisterna salva = cisternaRepository.save(toEntity(dto));
        return toDto(salva);
    }

    @Override
    public List<CisternaDto> listar() {
        return cisternaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CisternaDto buscarPorId(UUID id) {
        Cisterna cisterna = cisternaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cisterna não encontrada"));
        return toDto(cisterna);
    }

    @Override
    public CisternaDto atualizar(UUID id, CisternaDto dto) {
        Cisterna existente = cisternaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cisterna não encontrada"));

        Familia familia = familiaRepository.findById(dto.getId_familia())
                .orElseThrow(() -> new EntityNotFoundException("Família não encontrada"));

        existente.setCapacidadeLitros(dto.getCapacidadeLitros());
        existente.setNivelAtual(dto.getNivelAtual());
        existente.setFamilia(familia);

        Cisterna atualizada = cisternaRepository.save(existente);
        return toDto(atualizada);
    }

    @Override
    public void deletarPorId(UUID id) {
        Cisterna cisterna = cisternaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cisterna não encontrada"));
        cisternaRepository.delete(cisterna);
    }

    @Override
    public List<CisternaDto> listarPorFamilia(UUID idFamilia) {
        return cisternaRepository.findByFamiliaId(idFamilia).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}