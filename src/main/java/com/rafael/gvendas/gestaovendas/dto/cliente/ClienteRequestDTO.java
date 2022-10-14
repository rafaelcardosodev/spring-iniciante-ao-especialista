package com.rafael.gvendas.gestaovendas.dto.cliente;

import com.rafael.gvendas.gestaovendas.entities.Cliente;
import com.rafael.gvendas.gestaovendas.entities.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Schema(name = "Cliente Request DTO")
public class ClienteRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    @NotBlank(message = "Telefone")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "Telefone")
    private String telefone;

    @NotNull(message = "Ativo")
    private Boolean ativo;

    @NotNull(message = "Endereço")
    @Valid
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

    public Cliente convertToEntity(Long codigo) {
        return new Cliente(
                codigo,
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
