package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
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
import java.util.stream.Collectors;

@Api(tags = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @ApiOperation(value = "Listar todos", nickname = "get-all-produto")
    @GetMapping
    public List<ProdutoResponseDTO> getAll() {
        return service.getAll().stream()
                .map(p -> ProdutoResponseDTO.convertToProdutoDTO(p))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar todos por categoria", nickname = "get-all-by-categoria")
    @GetMapping("/categoria{codigoCategoria}")
    public List<ProdutoResponseDTO> getAllByCategoria(@PathVariable Long codigoCategoria) {
        return service.getAllByCategoria(codigoCategoria).stream()
                .map(p -> ProdutoResponseDTO.convertToProdutoDTO(p))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código", nickname = "find-by-codigo-produto")
    @GetMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> findByCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        Optional<Produto> produto = service.findByCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.convertToProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "save-produto")
    @PostMapping("/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO, @PathVariable Long codigoCategoria) {
        Produto produto = service.save(produtoRequestDTO.convertToEntity(codigoCategoria), codigoCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.convertToProdutoDTO(produto));
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizar-produto")
    @PutMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long codigo, @PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = service.update(produtoRequestDTO.convertToEntity(codigoCategoria, codigo), codigo, codigoCategoria);
        return ResponseEntity.ok(ProdutoResponseDTO.convertToProdutoDTO(produto));
    }

    @ApiOperation(value = "Deletar", nickname = "deletar-produto")
    @DeleteMapping("/{codigo}/categoria{codigoCategoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        service.delete(codigo, codigoCategoria);
    }
}
