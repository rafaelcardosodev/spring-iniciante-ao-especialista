package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.services.ProdutoService;
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

@Tag(name = "Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Operation(summary = "Listar todos")
    @GetMapping
    public List<ProdutoResponseDTO> getAll() {
        return service.getAll().stream()
                .map(p -> ProdutoResponseDTO.convertToProdutoDTO(p))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Listar todos por categoria")
    @GetMapping("/categoria{codigoCategoria}")
    public List<ProdutoResponseDTO> getAllByCategoria(@PathVariable Long codigoCategoria) {
        return service.getAllByCategoria(codigoCategoria).stream()
                .map(p -> ProdutoResponseDTO.convertToProdutoDTO(p))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Listar por código")
    @GetMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> findByCodigo(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        Optional<Produto> produto = service.findByCodigo(codigo, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.convertToProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar")
    @PutMapping("/{codigo}/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long codigo, @PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = service.update(produtoRequestDTO.convertToEntity(codigoCategoria, codigo), codigo, codigoCategoria);
        return ResponseEntity.ok(ProdutoResponseDTO.convertToProdutoDTO(produto));
    }

    @Operation(summary = "Salvar")
    @PostMapping("/categoria{codigoCategoria}")
    public ResponseEntity<ProdutoResponseDTO> save(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO, @PathVariable Long codigoCategoria) {
        Produto produto = service.save(produtoRequestDTO.convertToEntity(codigoCategoria), codigoCategoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.convertToProdutoDTO(produto));
    }

    @Operation(summary = "Deletar")
    @DeleteMapping("/{codigo}/categoria{codigoCategoria}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo, @PathVariable Long codigoCategoria) {
        service.delete(codigo, codigoCategoria);
    }
}
