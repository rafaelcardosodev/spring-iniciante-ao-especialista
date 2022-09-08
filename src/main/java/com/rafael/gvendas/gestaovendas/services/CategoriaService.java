package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> getAll() {
        return repository.findAll();
    }

    public Optional<Categoria> getById(Long codigo) {
        return repository.findById(codigo);
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria update(Long codigo, Categoria categoria) {
        Categoria _categoria = validateIfExists(codigo);
        BeanUtils.copyProperties(categoria, _categoria, "codigo");
        return repository.save(_categoria);
    }

    public void delete(Long codigo) {
        repository.deleteById(codigo);
    }

    private Categoria validateIfExists(Long codigo) {
        Optional<Categoria> categoria = getById(codigo);
        if (categoria.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            return categoria.get();
        }
    }
}
