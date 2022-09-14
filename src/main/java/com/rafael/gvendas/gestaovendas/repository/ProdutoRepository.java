package com.rafael.gvendas.gestaovendas.repository;

import com.rafael.gvendas.gestaovendas.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    List<Produto> findByCategoriaCodigo(Long codigoCategoria);

    @Query("select prod " +
            "from Produto prod " +
            "where prod.codigo = :codigo" +
            " and prod.categoria.codigo = :codigoCategoria")
    Optional<Produto> findByCodigo(Long codigo, Long codigoCategoria);

    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}
