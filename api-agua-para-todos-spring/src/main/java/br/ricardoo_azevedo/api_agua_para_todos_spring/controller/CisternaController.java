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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.CisternaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls.CisternaServiceImpl;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cisternas")
public class CisternaController {

    @Autowired
    private CisternaServiceImpl cisternaServiceImpl;

    @PostMapping
    public ResponseEntity<?> registrarCisterna(@RequestBody @Valid CisternaDto cisternaDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        CisternaDto cisternaSalva = cisternaServiceImpl.salvar(cisternaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cisternaSalva);
 
    }
    
    @PutMapping("/editar-id/{id}")
    public ResponseEntity<?> editarCisterna (@RequestBody @Valid CisternaDto cisternaDto, BindingResult bindingResult, @PathVariable UUID id) {
       if (bindingResult.hasErrors()) {
        return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
       }
       CisternaDto cisternaEditada = cisternaServiceImpl.editarPorId(cisternaDto, id);
       return ResponseEntity.status(HttpStatus.OK).body(cisternaEditada);
     }


     @GetMapping
     public ResponseEntity<List<?>> listarCisternas(){
        List<CisternaDto> listaCisternas = cisternaServiceImpl.listar();
        return ResponseEntity.ok().body(listaCisternas);
     }

    @GetMapping("/pesquisar-id/{id}")
     public ResponseEntity<?> buscarCisternaId(@PathVariable UUID id){
        CisternaDto resultado = cisternaServiceImpl.pesquisarPorId(id);
        return ResponseEntity.ok().body(resultado);
    }
     

     @DeleteMapping("/deletar-id/{id}")
     public ResponseEntity<?> deletarCisternaId(@PathVariable UUID id) {
        cisternaServiceImpl.deletarPorId(id);
        return ResponseEntity.noContent().build();
     }

}