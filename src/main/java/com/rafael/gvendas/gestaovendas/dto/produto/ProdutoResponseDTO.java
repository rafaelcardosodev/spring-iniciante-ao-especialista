package com.rafael.gvendas.gestaovendas.dto.produto;

import com.rafael.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "Produto response DTO")
public class ProdutoResponseDTO {

    @Schema(name = "Código")
    private Long codigo;

    @Schema(name = "Descrição")
    private String descricao;

    @Schema(name = "Quantidade")
    private Integer quantidade;

    @Schema(name = "Preço de custo")
    private BigDecimal precoCusto;

    @Schema(name = "Preço de venda")
    private BigDecimal precoVenda;

    @Schema(name = "Observação")
    private String observacao;

    @Schema(name = "Categoria")
    private CategoriaResponseDTO categoria;

    public ProdutoResponseDTO() {}

    public ProdutoResponseDTO(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto,
                              BigDecimal precoVenda, String observacao, CategoriaResponseDTO categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

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

    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDTO categoria) {
        this.categoria = categoria;
    }

    public static ProdutoResponseDTO convertToProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getCodigo(),
                produto.getDescricao(),
                produto.getQuantidade(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda(),
                produto.getObservacao(),
                CategoriaResponseDTO.convertToCategoriaDTO(produto.getCategoria())
        );
    }
}
