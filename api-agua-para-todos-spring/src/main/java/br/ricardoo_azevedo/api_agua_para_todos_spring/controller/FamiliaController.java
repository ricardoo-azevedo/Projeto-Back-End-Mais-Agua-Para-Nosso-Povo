package br.ricardoo_azevedo.api_agua_para_todos_spring.controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ricardoo_azevedo.api_agua_para_todos_spring.dtos.FamiliaDto;
import br.ricardoo_azevedo.api_agua_para_todos_spring.service.impls.FamiliaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/familias")
public class FamiliaController{

    @Autowired
    private FamiliaServiceImpl familiaServiceImpl;

    @PostMapping()
    public ResponseEntity<?> registrarFornecimento (@RequestBody @Valid FamiliaDto familiaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FamiliaDto familiaSalva = familiaServiceImpl.salvar(familiaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(familiaSalva);
   }

    @PutMapping("/editar-id/{id}")
    public ResponseEntity<?> editarFamilia (@RequestBody @Valid FamiliaDto familiaDto, BindingResult bindingResult, @PathVariable UUID id) {
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        FamiliaDto familaEditada = familiaServiceImpl.editarPorId(familiaDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(familaEditada);
   }

    @GetMapping
    public ResponseEntity<List<?>> listarFamilias(){
        List<FamiliaDto>  familiaDtos = familiaServiceImpl.listar(); 
        return ResponseEntity.ok(familiaDtos);
   }


   @GetMapping("/pesquisar-id/{id}")
    public ResponseEntity<?> buscarFamiliasId(@PathVariable UUID id) {
        FamiliaDto familiaDto = familiaServiceImpl.pesquisarPorId(id);
        return ResponseEntity.ok().body(familiaDto);
    }

    @GetMapping("/pesquisar-nis/{nis}")
    public ResponseEntity<?> buscarFamiliasId(@PathVariable String nis) {
        FamiliaDto familiaDto = familiaServiceImpl.pesquisarPorNis(nis);
        return ResponseEntity.ok().body(familiaDto);
    }

    @DeleteMapping("/deletar-id/{id}")
    public ResponseEntity<?> deletarFamiliaId(@PathVariable UUID id) {
        familiaServiceImpl.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
