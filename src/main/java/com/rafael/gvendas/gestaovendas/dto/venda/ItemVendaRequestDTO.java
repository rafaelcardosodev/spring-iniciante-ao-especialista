package com.rafael.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(name = "Item venda request DTO")
public class ItemVendaRequestDTO {

    @NotNull(message = "Código produto")
    private Long codigoProduto;

    @NotNull(message = "Quantidade")
    @Min(value = 1, message = "Quantidade")
    private Integer quantidade;

    @NotNull(message = "Preço vendido")
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
