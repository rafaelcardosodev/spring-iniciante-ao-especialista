package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Listar todos", nickname = "get-all-produto")
    @GetMapping
    public List<Produto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Listar todos por categoria", nickname = "get-all-by-categoria")
    @GetMapping("/categoria{codigoCategoria}")
    public List<Produto> getAllByCategoria(@PathVariable Long codigoCategoria) {
        return service.getAllByCategoria(codigoCategoria);
    }

    @ApiOperation(value = "Listar por código", nickname = "find-by-codigo")
    @GetMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<Optional<Produto>> findByCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        Optional<Produto> produto = service.findByCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save-produto")
    @PostMapping("/categoria{codigoCategoria}")
    public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto, @PathVariable Long codigoCategoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(produto, codigoCategoria));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar-produto")
    @PutMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<Produto> update(@PathVariable Long codigo, @PathVariable Long codigoCategoria, @Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(service.update(produto, codigo, codigoCategoria));
    }
}
