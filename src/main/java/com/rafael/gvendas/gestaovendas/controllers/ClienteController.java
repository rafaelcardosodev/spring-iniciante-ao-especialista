package com.rafael.gvendas.gestaovendas.controllers;

import com.rafael.gvendas.gestaovendas.dto.cliente.ClienteRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Cliente;
import com.rafael.gvendas.gestaovendas.services.ClienteService;
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

@Tag(name = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Listar todos")
    @GetMapping()
    public List<ClienteResponseDTO> getAll() {
        return service.getAll().stream()
                .map(cliente -> ClienteResponseDTO.convertToClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Listar por código")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long codigo) {
        Optional<Cliente> cliente = service.getById(codigo);
        return cliente.isPresent()
                ? ResponseEntity.ok(ClienteResponseDTO.convertToClienteDTO(cliente.get()))
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Salvar")
    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> save(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente _cliente = service.save(clienteRequestDTO.convertToEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.convertToClienteDTO(_cliente));
    }

    @Operation(summary = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {
        Cliente _cliente = service.update(codigo, clienteRequestDTO.convertToEntity(codigo));
        return ResponseEntity.ok().body(ClienteResponseDTO.convertToClienteDTO(_cliente));
    }

    @Operation(summary = "Deletar")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigo) {
        service.delete(codigo);
    }

}
