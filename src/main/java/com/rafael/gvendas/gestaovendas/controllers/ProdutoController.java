package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Listar todos", nickname = "getAll")
    @GetMapping
    public List<Produto> getAll() {
        return service.getAll();
    }

    @ApiOperation(value = "Listar todos por categoria", nickname = "getAllByCategoria")
    @GetMapping("/categoria{codigoCategoria}")
    public List<Produto> getAllByCategoria(@PathVariable Long codigoCategoria) {
        return service.getAllByCategoria(codigoCategoria);
    }

    @ApiOperation(value = "Listar por código", nickname = "findByCodigo")
    @GetMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<Optional<Produto>> findByCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        Optional<Produto> produto = service.findByCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }
}
