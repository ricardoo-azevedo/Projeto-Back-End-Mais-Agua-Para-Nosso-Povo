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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.DistribuicaoDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls.DistribuicaoServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/distribuicoes")
public class DistribuicaoController {


    @Autowired
    private DistribuicaoServiceImpl distribuicaoServiceImpl;


    @PostMapping
    public ResponseEntity<?> registrarDistribuicao (@RequestBody @Valid DistribuicaoDto distribuicaoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        DistribuicaoDto distribuicaoSalva = distribuicaoServiceImpl.salvar(distribuicaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(distribuicaoSalva);
    }

    @GetMapping
    public ResponseEntity<List<?>> listarDistribuicoes(){
       List<DistribuicaoDto> listaDistribuicoes = distribuicaoServiceImpl.listar(); 
       return ResponseEntity.ok().body(listaDistribuicoes);
    }

    @DeleteMapping("/deletar-id/{id}")
    public ResponseEntity<?> deletarDistribuicaoId(@PathVariable UUID id){
        distribuicaoServiceImpl.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}