package com.rafael.gvendas.gestaovendas.repository;

import com.rafael.gvendas.gestaovendas.entities.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query("SELECT NEW com.rafael.gvendas.gestaovendas.entities.ItemVenda(" +
            "iv.codigo, iv.quantidade, iv.precoVendido, iv.produto, iv.venda)" +
            " FROM ItemVenda iv" +
            " WHERE iv.venda.codigo = :codigoVenda")
    List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}
