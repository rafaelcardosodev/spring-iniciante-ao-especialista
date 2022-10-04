package com.rafael.gvendas.gestaovendas.dto.produto;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(name = "Produto request DTO")
public class ProdutoRequestDTO {

    @Schema(name = "Descrição")
    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    private String descricao;

    @Schema(name = "Quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;

    @Schema(name = "Preço de custo")
    @NotNull(message = "Preço de custo")
    private BigDecimal precoCusto;

    @Schema(name = "Preço de venda")
    @NotNull(message = "Preço de venda")
    private BigDecimal precoVenda;

    @Schema(name = "Observação")
    @Length(max = 500, message = "Observação")
    private String observacao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public Produto convertToEntity(Long codigoCategoria) {
        return new Produto(
                this.descricao,
                this.quantidade,
                this.precoCusto,
                this.precoVenda,
                this.observacao,
                new Categoria(codigoCategoria)
        );
    }

    public Produto convertToEntity(Long codigoCategoria, Long codigoProduto) {
        return new Produto(
                codigoProduto,
                this.descricao,
                this.quantidade,
                this.precoCusto,
                this.precoVenda,
                this.observacao,
                new Categoria(codigoCategoria)
        );
    }
}
