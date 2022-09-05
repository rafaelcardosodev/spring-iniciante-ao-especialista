package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping()
    public List<Categoria> getAll() {
        return service.getAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria>> getById(@PathVariable Long codigo) {
        Optional<Categoria> categoria = service.getById(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
        Categoria _categoria = service.save(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(_categoria);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> update(@PathVariable Long codigo, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(service.update(codigo, categoria));
    }
}
