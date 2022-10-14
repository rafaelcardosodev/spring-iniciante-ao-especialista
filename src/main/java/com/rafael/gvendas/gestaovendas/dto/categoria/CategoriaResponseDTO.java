package com.rafael.gvendas.gestaovendas.dto.categoria;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Categoria Response DTO")
public class CategoriaResponseDTO {

    private Long codigo;


    private String nome;

    public CategoriaResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static CategoriaResponseDTO convertToCategoriaDTO(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getCodigo(), categoria.getNome());
    }
}
