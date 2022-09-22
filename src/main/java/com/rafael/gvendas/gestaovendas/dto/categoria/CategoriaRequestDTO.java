package com.rafael.gvendas.gestaovendas.dto.categoria;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel("Categoria Request DTO")
public class CategoriaRequestDTO {

    @ApiModelProperty(value = "Nome")
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
