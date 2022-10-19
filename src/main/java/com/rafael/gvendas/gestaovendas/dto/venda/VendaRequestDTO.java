package com.rafael.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "Venda request DTO")
public class VendaRequestDTO {

    private LocalDate data;
    private List<ItemVendaRequestDTO> itemVendaRequestDTOs;

    public VendaRequestDTO() {}

    public VendaRequestDTO(LocalDate data, List<ItemVendaRequestDTO> itemVendaRequestDTOs) {
        this.data = data;
        this.itemVendaRequestDTOs = itemVendaRequestDTOs;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<ItemVendaRequestDTO> getItemVendaRequestDTOs() {
        return itemVendaRequestDTOs;
    }

    public void setItemVendaRequestDTOs(List<ItemVendaRequestDTO> itemVendaRequestDTOs) {
        this.itemVendaRequestDTOs = itemVendaRequestDTOs;
    }
}
