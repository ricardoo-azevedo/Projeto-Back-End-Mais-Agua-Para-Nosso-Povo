package br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.MembroDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.models.Membro;
import br.ricardoo_azevedo.api_agua_para_todos_spring.repositorys.MembroRepository;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.interfaces.MembroServiceInterface;

@Service
public class MembroServiceImpl implements MembroServiceInterface{

    @Autowired
    MembroRepository membroRepository; 

    public MembroDto toDto (Membro membro) {
       return new MembroDto(
        membro.getId(),
        membro.getCpf(),
        membro.getNome(),
        membro.getAno_nascimento(),
        membro.isAcamado()); 
    }

    public Membro toEntity (MembroDto membroDto){
       return new Membro(
        membroDto.getCpf(),
        membroDto.getNome(),
        membroDto.getAno_nascimento(),
        membroDto.isAcamado()); 
    }

    @Override
    public MembroDto salvar(MembroDto membroDto) {
        /* if (membroRepository.existsByCpf(membroDto.getCpf())){
           throw new Exception(); 
        } */ 
       Membro membroSalvo = membroRepository.save(toEntity(membroDto));
       return toDto(membroSalvo);
    }

    @Override
    public MembroDto editarPorId(MembroDto membroDto, UUID id) {
        /* if (id== null){
            throw new Expection();
        } */
       Membro membro = membroRepository.findById(id).orElseThrow(() -> new RuntimeException());

       membro.setCpf(membroDto.getCpf());
       membro.setNome(membroDto.getNome());
       membro.setAno_nascimento(membroDto.getAno_nascimento());
       membro.setAcamado(membroDto.isAcamado());

       Membro membroEditado = membroRepository.save(membro);

       return toDto(membroEditado);
    }

    @Override
    public List<MembroDto> listar() {
        List<Membro> membros = membroRepository.findAll();
        return membros.stream()
        .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public MembroDto pesquisarPorId(UUID id) {
        /* if (id == null){
            throw new Exception();
        } */
        return membroRepository.findById(id)
        .map(this::toDto).orElseThrow(() -> new RuntimeException());

    }

    @Override
    public MembroDto pesquisarPorCpf(String cpf) {
       /* if(cpf == null) {
        throw new Exception(); 
       } */
        return membroRepository.findByCpf(cpf).map(this::toDto).orElseThrow(() -> new RuntimeException());

    }

    @Override
    public List<MembroDto> pesquisarPorNome(String nome) {
        List<Membro> membros = membroRepository.findByNomeContaining(nome);
        return membros.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletarPorId(UUID id) {
        /* if (id == null) {
            throw new IdInvalidoException("O id é nulo");
        }
        if (familiaRepository.existsById(id) == false) {
            throw new NaoEncontradaException();
        } */
       membroRepository.deleteById(id);
    }

}