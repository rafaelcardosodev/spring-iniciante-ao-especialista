package com.rafael.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "Venda response DTO")
public class VendaResponseDTO {

    private Long codigo;
    private LocalDate data;
    private List<ItemVendaResponseDTO> itemVendaResponseDTOs;

    public VendaResponseDTO() {}

    public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaResponseDTOs) {
        this.codigo = codigo;
        this.data = data;
        this.itemVendaResponseDTOs = itemVendaResponseDTOs;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaResponseDTO> getItemVendaResponseDTOs() {
        return itemVendaResponseDTOs;
    }

    public void setItemVendaResponseDTOs(List<ItemVendaResponseDTO> itemVendaResponseDTOs) {
        this.itemVendaResponseDTOs = itemVendaResponseDTOs;
    }
}
