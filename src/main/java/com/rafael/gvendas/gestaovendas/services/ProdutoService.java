package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.exceptions.BusinessRuleException;
import com.rafael.gvendas.gestaovendas.exceptions.GestaoVendasExceptionHandler;
import com.rafael.gvendas.gestaovendas.services.repository.ProdutoRepository;
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

    @Autowired
    private CategoriaService categoriaService;

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
        validateIfCategoriaExists(produto.getCategoria().getCodigo());
        validateIfExists(produto);
        return repository.save(produto);
    }

//    public Produto update(Produto produto, Long codigo, Long codigoCategoria) {
//        Produto _produto = validateIfExists(codigo, codigoCategoria);
//        BeanUtils.copyProperties(produto, _produto, "codigo");
//        return repository.save(_produto);
//    }

    public void delete(Long codigo) {
        repository.deleteById(codigo);
    }

    private void validateIfCategoriaExists(Long codigoCategoria) {
        if (codigoCategoria == null) throw new BusinessRuleException("A categoria não pode ser nula");
        if (categoriaService.getById(codigoCategoria).isEmpty()) throw new BusinessRuleException(String.format("Esta categoria de código %s não existe", codigoCategoria));
    }

    private void validateIfExists(Produto produto) {
        Categoria categoria = produto.getCategoria();
        if (repository.findByCategoriaCodigoAndDescricao(categoria.getCodigo(), produto.getDescricao()).isPresent()) {
            throw new BusinessRuleException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }


}
