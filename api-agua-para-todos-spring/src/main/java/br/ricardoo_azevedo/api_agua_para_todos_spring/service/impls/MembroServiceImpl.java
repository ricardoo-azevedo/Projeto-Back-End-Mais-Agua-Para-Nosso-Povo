package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.MembroDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Familia;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Membro;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.FamiliaRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.MembroRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.MembroServiceInterface;

@Service
public class MembroServiceImpl implements MembroServiceInterface {

    @Autowired
    MembroRepository membroRepository;

    @Autowired
    FamiliaRepository familiaRepository;

    private MembroDto toDto(Membro m) {
        return new MembroDto(
            m.getId(),
            m.getCpf(),
            m.getNome(),
            m.getAno_nascimento(),
            m.isAcamado(),
            m.getFamilia().getId()
        );
    }

    private Membro toEntity(MembroDto dto) {
        Familia familia = familiaRepository.findById(dto.getId_familia())
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));

        return new Membro(
            dto.getCpf(),
            dto.getNome(),
            dto.getAno_nascimento(),
            dto.getAcamado(),
            familia
        );
    }

    @Override
    public MembroDto salvar(MembroDto dto) {
        if (membroRepository.existsByCpf(dto.getCpf()))
            throw new RuntimeException("CPF já existe");

        Membro salvo = membroRepository.save(toEntity(dto));
        return toDto(salvo);
    }

    @Override
    public MembroDto editarPorId(MembroDto dto, UUID id) {
        Membro membro = membroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));

        Familia familia = familiaRepository.findById(dto.getId_familia())
                .orElseThrow(() -> new RuntimeException("Família não encontrada"));

        membro.setCpf(dto.getCpf());
        membro.setNome(dto.getNome());
        membro.setAno_nascimento(dto.getAno_nascimento());
        membro.setAcamado(dto.getAcamado());
        membro.setFamilia(familia);

        return toDto(membroRepository.save(membro));
    }

    @Override
    public List<MembroDto> listar() {
        return membroRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MembroDto pesquisarPorId(UUID id) {
        return membroRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));
    }

    @Override
    public MembroDto pesquisarPorCpf(String cpf) {
        return membroRepository.findByCpf(cpf)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado"));
    }

    @Override
    public List<MembroDto> pesquisarPorNome(String nome) {
        return membroRepository.findByNomeContaining(nome)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
         Membro membro = membroRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Membro não encontrado"));
        Familia familia = membro.getFamilia();

        if (familia != null) {
        familia.getMembros().remove(membro);
        familiaRepository.save(familia); // aciona o orphanRemoval
        }
        membroRepository.delete(membro);
    }
}