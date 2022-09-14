package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.CategoriaRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.CategoriaResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.services.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @ApiOperation(value = "Listar todos", nickname = "get-all-categoria")
    @GetMapping()
    public List<CategoriaResponseDTO> getAll() {
        return service.getAll().stream()
                .map(c -> CategoriaResponseDTO.convertToCategoriaDTO(c))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código", nickname = "get-by-id-categoria")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = service.getById(codigo);
        return categoria.isPresent()
                ? ResponseEntity.ok(CategoriaResponseDTO.convertToCategoriaDTO(categoria.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save-categoria")
    @PostMapping()
    public ResponseEntity<CategoriaResponseDTO> save(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        Categoria _categoria = service.save(categoriaDTO.convertToEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.convertToCategoriaDTO(_categoria));
    }

    @ApiOperation(value = "Atualizar", nickname = "update-categoria")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        return ResponseEntity.ok(service.update(codigo, categoriaDTO.convertToEntity(codigo)));
    }

    @ApiOperation(value = "Deletar", nickname = "delete-categoria")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        service.delete(codigo);
    }
}
