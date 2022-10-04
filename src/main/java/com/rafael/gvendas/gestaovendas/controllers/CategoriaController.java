package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Listar todos")
    @GetMapping()
    public List<CategoriaResponseDTO> getAll() {
        return service.getAll().stream()
                .map(c -> CategoriaResponseDTO.convertToCategoriaDTO(c))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = service.getById(codigo);
        return categoria.isPresent()
                ? ResponseEntity.ok(CategoriaResponseDTO.convertToCategoriaDTO(categoria.get()))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar")
    @PostMapping()
    public ResponseEntity<CategoriaResponseDTO> save(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        Categoria _categoria = service.save(categoriaDTO.convertToEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.convertToCategoriaDTO(_categoria));
    }

    @Operation(summary = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        Categoria categoria = service.update(codigo, categoriaDTO.convertToEntity(codigo));
        return ResponseEntity.ok(CategoriaResponseDTO.convertToCategoriaDTO(categoria));
    }

    @Operation(summary = "Deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        service.delete(codigo);
    }
}
