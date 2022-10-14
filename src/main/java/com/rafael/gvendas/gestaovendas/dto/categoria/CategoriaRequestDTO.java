package com.rafael.gvendas.gestaovendas.dto.categoria;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Schema(name = "Categoria Request DTO")
public class CategoriaRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    public CategoriaRequestDTO() {}

    public CategoriaRequestDTO(String nome) {
        this.nome = nome;
    }

    public Categoria convertToEntity() {
        return new Categoria(this.nome);
    }

    public Categoria convertToEntity(Long codigo) {
        return new Categoria(codigo, this.nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
