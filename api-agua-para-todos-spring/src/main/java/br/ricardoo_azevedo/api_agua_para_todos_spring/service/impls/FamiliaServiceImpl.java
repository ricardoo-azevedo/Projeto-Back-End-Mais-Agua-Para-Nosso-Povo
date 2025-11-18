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

    public FamiliaDto toDto (Familia familia){
        return new FamiliaDto(
          familia.getId(), 
          familia.getEndereco(), 
          familia.isPossui_captacao_calhas(),
          familia.getData_entrega(), 
          familia.getProxima_previsao(), 
          familia.getLatitude(),
          familia.getLongitude());
    }

    public Familia toEntity(FamiliaDto familiaDto){
     return new Familia(
          familiaDto.getEndereco(), 
          familiaDto.isPossui_captacao_calhas(),
          familiaDto.getData_entrega(), 
          familiaDto.getProxima_previsao(), 
          familiaDto.getLatitude(),
          familiaDto.getLongitude());
    }


  @Override
  public FamiliaDto salvar(FamiliaDto familiaDto) {
    // if (familiaRepository.existsById(familiaDto.getId() {
    // throw new Exception();
    // }
    Familia familiaSalva = familiaRepository.save(toEntity(familiaDto));
    return toDto(familiaSalva);
  }

  @Override
  public FamiliaDto editarPorId(FamiliaDto familiaDto, UUID id) {
    // if(id == null){
    //   throw new expcetion;
    // }
    Familia familia = familiaRepository.findById(id).orElseThrow(() -> new RuntimeException());

    familia.setEndereco(familiaDto.getEndereco());
    familia.setPossui_captacao_calhas(familiaDto.isPossui_captacao_calhas());
    familia.setData_entrega(familiaDto.getData_entrega());
    familia.setProxima_previsao(familiaDto.getProxima_previsao());
    familia.setLatitude(familiaDto.getLatitude());
    familia.setLongitude(familiaDto.getLongitude()); 

    Familia familiaEditada = familiaRepository.save(familia);
    return toDto(familiaEditada);
  }

  @Override
  public List<FamiliaDto> listar() {
   List<Familia> familias = familiaRepository.findAll(); 
   return familias.stream()
   .map(this::toDto).collect(Collectors.toList());

  }

  @Override
  public FamiliaDto pesquisarPorId(UUID id) {
    // if(id == null){
    //   throw new expcetion;
    // }
    return familiaRepository.findById(id)
    .map(this::toDto).orElseThrow(() -> new RuntimeException("Achou nao"));
  }

  @Override
  public List<FamiliaDto> pesquisarPorEndereco(String endereco) {
    List<Familia> resultados = familiaRepository.findByEnderecoContaining(endereco);
    return resultados.stream().map(this::toDto).collect(Collectors.toList());
  }

  @Override
  public void deletarPorId(UUID id) {
   /* if (id == null) {
            throw new IdInvalidoException("O id é nulo");
        }
        if (familiaRepository.existsById(id) == false) {
            throw new EscolaNaoEncontradaException();
        } */
        familiaRepository.deleteById(id);
    }
  
  }


