package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.FamiliaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.FamiliaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.FamiliaServiceInterface;

@Service
public class FamiliaServiceImpl implements FamiliaServiceInterface {

    @Autowired
    FamiliaRepository familiaRepository;

    public FamiliaDto toDto(Familia f) {
        return new FamiliaDto(f.getId(), f.getNis(), f.getEndereco(), f.getLatitude(), f.getLongitude(), f.isPossui_captacao_calhas());
    }

    public Familia toEntity(FamiliaDto dto) {
        return new Familia(
         dto.getNis(),
         dto.getEndereco(), 
         dto.getLatitude(), 
         dto.getLongitude(), 
         dto.isPossui_captacao_calhas());
    }

    @Override
    public FamiliaDto salvar(FamiliaDto dto) {
        if (familiaRepository.existsByNis(dto.getNis()))
            throw new RuntimeException("NIS já existe");

        Familia salva = familiaRepository.save(toEntity(dto));
        return toDto(salva);
    }

    @Override
    public FamiliaDto editarPorId(FamiliaDto dto, UUID id) {
        Familia familia = familiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));

        familia.setNis(dto.getNis());
        familia.setEndereco(dto.getEndereco());
        familia.setLatitude(dto.getLatitude());
        familia.setLongitude(dto.getLongitude());
        familia.setPossui_captacao_calhas(dto.isPossui_captacao_calhas());

        return toDto(familiaRepository.save(familia));
    }

    @Override
    public List<FamiliaDto> listar() {
        return familiaRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public FamiliaDto pesquisarPorId(UUID id) {
        return familiaRepository.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));
    }

    @Override
    public FamiliaDto pesquisarPorNis(String nis) {
        return familiaRepository.findByNis(nis).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));
    }

    @Override
    public void deletarPorId(UUID id) {
        if (!familiaRepository.existsById(id))
            throw new RuntimeException("Família não encontrada");

        familiaRepository.deleteById(id);
    }
}