package com.rafael.gvendas.gestaovendas.controllers;

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

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @ApiOperation(value = "Listar todos")
    @GetMapping()
    public List<Categoria> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = service.getById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping()
    public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
        Categoria _categoria = service.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(_categoria);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
        return ResponseEntity.ok(service.update(codigo, categoria));
    }

    @ApiOperation(value = "Deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        service.delete(codigo);
    }
}
