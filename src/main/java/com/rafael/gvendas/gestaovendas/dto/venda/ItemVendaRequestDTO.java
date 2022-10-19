package com.rafael.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "Item venda request DTO")
public class ItemVendaRequestDTO {

    private Long codigoProduto;
    private Integer quantidade;
    private BigDecimal precoVendido;

    public ItemVendaRequestDTO() {}

    public ItemVendaRequestDTO(Long codigoProduto, Integer quantidade, BigDecimal precoVendido) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoVendido() {
        return precoVendido;
    }

    public void setPrecoVendido(BigDecimal precoVendido) {
        this.precoVendido = precoVendido;
    }
}
