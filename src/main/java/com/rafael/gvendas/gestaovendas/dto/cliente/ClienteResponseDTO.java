package com.rafael.gvendas.gestaovendas.dto.cliente;

import com.rafael.gvendas.gestaovendas.entities.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Cliente Response DTO")
public class ClienteResponseDTO {

    @Schema(name = "Código")
    private Long codigo;

    @Schema(name = "Nome")
    private String nome;

    @Schema(name = "Telefone")
    private String telefone;

    @Schema(name = "Ativo")
    private Boolean ativo;

    private EnderecoResponseDTO enderecoResponseDTO;

    public ClienteResponseDTO(){}

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo, EnderecoResponseDTO enderecoResponseDTO) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoResponseDTO = enderecoResponseDTO;
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

    public EnderecoResponseDTO getEnderecoResponseDTO() {
        return enderecoResponseDTO;
    }

    public void setEnderecoResponseDTO(EnderecoResponseDTO enderecoResponseDTO) {
        this.enderecoResponseDTO = enderecoResponseDTO;
    }

    public static ClienteResponseDTO convertToClienteDTO(Cliente cliente) {
        EnderecoResponseDTO enderecoDTO = new EnderecoResponseDTO(
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCep(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getEstado());
        return new ClienteResponseDTO(
                cliente.getCodigo(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getAtivo(),
                enderecoDTO);
    }
}
