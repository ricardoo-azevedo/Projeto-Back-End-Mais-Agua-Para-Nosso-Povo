package br.ricardoo_azevedo.api_agua_para_todos_spring.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.MembroDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls.MembroServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/membros")
public class MembroController {

    @Autowired
    private MembroServiceImpl membroServiceImpl;

    @PostMapping()
    public ResponseEntity<?> registrarMembro(@RequestBody @Valid MembroDto membroDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        MembroDto membroSalvo = membroServiceImpl.salvar(membroDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(membroSalvo);
    }

    @PutMapping("/editar-id/{id}")
    public ResponseEntity<?> editarMembro (@RequestBody @Valid MembroDto membroDto, BindingResult bindingResult, @PathVariable UUID id) {
       if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
       }
       MembroDto membroEditado = membroServiceImpl.editarPorId(membroDto, id);
       return ResponseEntity.status(HttpStatus.OK).body(membroEditado);
     }

     @GetMapping
     public ResponseEntity<List<?>> listarMembros(){
        List<MembroDto> listaMembros = membroServiceImpl.listar();
        return ResponseEntity.ok().body(listaMembros);
     }

     @GetMapping("/pesquisar-id/{id}")
     public ResponseEntity<?> buscarMembrosId(@PathVariable UUID id){
        MembroDto resultado = membroServiceImpl.pesquisarPorId(id);
        return ResponseEntity.ok().body(resultado);
     }

     @GetMapping("/pesquisar-cpf/{cpf}")
     public ResponseEntity<?> buscarMembrosCpf(@PathVariable String cpf) {
        MembroDto resultado = membroServiceImpl.pesquisarPorCpf(cpf);
        return ResponseEntity.ok().body(resultado);
     }

     @GetMapping("/pesquisar-nome/{nome}")
     public ResponseEntity<List<?>> buscarMembroNome (@PathVariable String nome){
        List<MembroDto> listaAproximada = membroServiceImpl.pesquisarPorNome(nome);
        return ResponseEntity.ok().body(listaAproximada);
     }

     @DeleteMapping("/deletar-id/{id}")
     public ResponseEntity<?> deletarMembroId(@PathVariable UUID id) {
        membroServiceImpl.deletarPorId(id);
        return ResponseEntity.noContent().build();
     }



     
}