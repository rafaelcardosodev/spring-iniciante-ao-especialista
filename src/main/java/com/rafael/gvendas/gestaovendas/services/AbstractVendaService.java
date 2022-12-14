package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.ItemVendaResponseDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.ItemVenda;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.entities.Venda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractVendaService {
    protected VendaResponseDTO createVendaResponseDTO(Venda venda, List<ItemVenda> itensVendas) {
        List<ItemVendaResponseDTO> itensVendaResponseDTO = itensVendas.stream()
                        .map(this::createItemVendaResponseDTO)
                        .collect(Collectors.toList());
        return new VendaResponseDTO(venda.getCodigo(), venda.getData(), itensVendaResponseDTO);
    }

    protected ItemVendaResponseDTO createItemVendaResponseDTO(ItemVenda itemVenda) {
        return new ItemVendaResponseDTO(
                itemVenda.getCodigo(),
                itemVenda.getQuantidade(),
                itemVenda.getPrecoVendido(),
                itemVenda.getProduto().getCodigo(),
                itemVenda.getProduto().getDescricao());
    }

    protected ClienteVendaResponseDTO createClienteVendaResponseDTO(Venda venda, List<ItemVenda> itensVenda) {
        return new ClienteVendaResponseDTO(venda.getCliente().getNome(), Arrays.asList(createVendaResponseDTO(venda, itensVenda)));
    }

    protected ItemVenda createItemVenda(ItemVendaRequestDTO itemVendaRequestDTO, Venda venda) {
        return new ItemVenda(
                itemVendaRequestDTO.getQuantidade(),
                itemVendaRequestDTO.getPrecoVendido(),
                new Produto(itemVendaRequestDTO.getCodigoProduto()),
                venda
        );
    }
}
