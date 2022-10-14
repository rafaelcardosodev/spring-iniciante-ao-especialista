package com.rafael.gvendas.gestaovendas.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(name = "Endereço request DTO")
public class EnderecoRequestDTO {

    @NotBlank(message = "Logradouro")
    @Length(min = 3, max = 30, message = "Nome")
    private String logradouro;

    @NotNull(message = "Número")
    private Integer numero;


    @Length(max = 30, message = "Complemento")
    private String complemento;

    @NotBlank(message = "Bairro")
    @Length(min = 3, max = 30, message = "Bairro")
    private String bairro;

    @NotBlank(message = "Cep")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "Cep")
    private String cep;

    @NotBlank(message = "Cidade")
    @Length(min = 3, max = 30, message = "Cidade")
    private String cidade;

    @NotBlank(message = "Estado")
    @Length(min = 3, max = 30, message = "Estado")
    private String estado;

    public EnderecoRequestDTO() {}

    public EnderecoRequestDTO(String logradouro, Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
