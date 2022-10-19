package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.rafael.gvendas.gestaovendas.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService service;

    @Operation(summary = "Listar todas vendas por cliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> getAllByCliente(@PathVariable Long codigoCliente) {
        return ResponseEntity.ok(service.getAllByCliente(codigoCliente));
    }

    @Operation(summary = "Listar venda por código")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> getVendaByCodigo(Long codigoVenda) {
        return ResponseEntity.ok(service.getVendaByCodigo(codigoVenda));
    }

    @Operation(summary = "Salvar")
    @PostMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> save(@PathVariable Long codigoCliente, @RequestBody VendaRequestDTO vendaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(codigoCliente, vendaRequestDTO));
    }

}
