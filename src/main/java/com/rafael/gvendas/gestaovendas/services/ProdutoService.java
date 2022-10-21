package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.exceptions.BusinessRuleException;
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

    public Produto save(Produto produto, Long codigoCategoria) {
        validateIfCategoriaExists(codigoCategoria);
        validateIfProdutoDuplicated(produto);
        return repository.save(produto);
    }

    public Produto update(Produto produto, Long codigo, Long codigoCategoria) {
        validateIfCategoriaExists(produto.getCategoria().getCodigo());
        validateIfProdutoDuplicated(produto);
        Produto _produto = validateIfExists(codigo, codigoCategoria);
        BeanUtils.copyProperties(produto, _produto, "codigo");
        return repository.save(_produto);
    }

    public void delete(Long codigo, Long codigoCategoria) {
        validateIfExists(codigo, codigoCategoria);
        repository.deleteById(codigo);
    }

    private void validateIfProdutoDuplicated(Produto produto) {
        Optional<Produto> _produto = repository.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if (_produto.isPresent() && _produto.get().getCodigo() != produto.getCodigo()) {
            throw new BusinessRuleException(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private void validateIfCategoriaExists(Long codigoCategoria) {
        if (codigoCategoria == null) throw new BusinessRuleException("A categoria não pode ser nula");
        if (categoriaService.getById(codigoCategoria).isEmpty()) throw new BusinessRuleException(String.format("Esta categoria de código %s não existe", codigoCategoria));
    }

    private Produto validateIfExists(Long codigo, Long codigoCategoria) {
        Optional<Produto> produto = findByCodigo(codigo, codigoCategoria);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return produto.get();
    }

    protected Produto validateIfExists(Long codigo) {
        Optional<Produto> produto = repository.findById(codigo);
        if (produto.isEmpty()) {
            throw new BusinessRuleException(String.format("produto de código %s não encontrado", codigo));
        }

        return produto.get();
    }

    protected void updateQuantidadeProdutoEmEstoque(Produto produto) {
        repository.save(produto);
    }
}
