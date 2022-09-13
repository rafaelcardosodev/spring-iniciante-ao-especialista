package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> getAll() {
        return repository.findAll();
    }

    public List<Produto> getAllByCategoria(Long codigoCategoria) {
        return repository.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> findByCodigo(Long codigo, Long codigoCategoria) {
        return repository.findByCodigo(codigo, codigoCategoria);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(Produto produto, Long codigo, Long codigoCategoria) {
        Produto _produto = validateIfExists(codigo, codigoCategoria);
        BeanUtils.copyProperties(produto, _produto, "codigo");
        return repository.save(_produto);
    }

    public void delete(Long codigo) {
        repository.deleteById(codigo);
    }

    private Produto validateIfExists(Long codigo, Long codigoCategoria) {
        Optional<Produto> produto = findByCodigo(codigo, codigoCategoria);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            return produto.get();
        }
    }
}
