package com.rafael.gvendas.gestaovendas.dto.cliente;

import com.rafael.gvendas.gestaovendas.entities.Cliente;
import com.rafael.gvendas.gestaovendas.entities.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Cliente Request DTO")
public class ClienteRequestDTO {

    @Schema(name = "Nome")
    private String nome;

    @Schema(name = "Telefone")
    private String telefone;

    @Schema(name = "Ativo")
    private Boolean ativo;

    @Schema(name = "Endereço")
    private EnderecoRequestDTO enderecoRequestDTO;

    public ClienteRequestDTO(){}

    public ClienteRequestDTO(String nome, String telefone, Boolean ativo, EnderecoRequestDTO enderecoRequestDTO) {
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoRequestDTO = enderecoRequestDTO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EnderecoRequestDTO getEnderecoRequestDTO() {
        return enderecoRequestDTO;
    }

    public void setEnderecoRequestDTO(EnderecoRequestDTO enderecoRequestDTO) {
        this.enderecoRequestDTO = enderecoRequestDTO;
    }

    public Cliente convertToEntity() {
        return new Cliente(
                this.nome,
                this.telefone,
                this.ativo,
                new Endereco(
                        enderecoRequestDTO.getLogradouro(),
                        enderecoRequestDTO.getNumero(),
                        enderecoRequestDTO.getComplemento(),
                        enderecoRequestDTO.getBairro(),
                        enderecoRequestDTO.getCep(),
                        enderecoRequestDTO.getCidade(),
                        enderecoRequestDTO.getEstado()
                )
        );
    }
}
