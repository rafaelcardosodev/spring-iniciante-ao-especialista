package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.entities.Cliente;
import com.rafael.gvendas.gestaovendas.exceptions.BusinessRuleException;
import com.rafael.gvendas.gestaovendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<Cliente> getAll() {
        return repository.findAll();
    }

    public Optional<Cliente> getById(Long codigo) {
        return repository.findById(codigo);
    }

    public Cliente save(Cliente cliente) {
        validateIfExists(cliente);
        return repository.save(cliente);
    }

    private void validateIfExists(Cliente cliente) {
        Cliente _cliente =  repository.findByNome(cliente.getNome());
        if (_cliente != null && _cliente.getCodigo() != cliente.getCodigo()) {
            throw new BusinessRuleException(String.format("O cliente %s já está cadastrado", cliente.getNome().toUpperCase()));
        }
    }
}
