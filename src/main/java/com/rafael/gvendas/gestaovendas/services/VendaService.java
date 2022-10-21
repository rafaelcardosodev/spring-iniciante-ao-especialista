package com.rafael.gvendas.gestaovendas.services;

import com.rafael.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.rafael.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import com.rafael.gvendas.gestaovendas.entities.Cliente;
import com.rafael.gvendas.gestaovendas.entities.ItemVenda;
import com.rafael.gvendas.gestaovendas.entities.Produto;
import com.rafael.gvendas.gestaovendas.entities.Venda;
import com.rafael.gvendas.gestaovendas.exceptions.BusinessRuleException;
import com.rafael.gvendas.gestaovendas.repository.ItemVendaRepository;
import com.rafael.gvendas.gestaovendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService extends AbstractVendaService{

    private VendaRepository repository;
    private ItemVendaRepository itemVendaRepository;
    private ClienteService clienteService;
    private ProdutoService produtoService;

    @Autowired
    public VendaService(VendaRepository repository, ClienteService clienteService, ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.repository = repository;
        this.clienteService = clienteService;
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    public ClienteVendaResponseDTO getAllByCliente(Long codigoCliente) {
        Cliente cliente = validateIfClienteExists(codigoCliente);
        List<VendaResponseDTO> vendasResponseDTO = repository.findByClienteCodigo(codigoCliente).stream()
                .map(venda -> createVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(venda.getCodigo())))
                .collect(Collectors.toList());
        return new ClienteVendaResponseDTO(cliente.getNome(), vendasResponseDTO);
    }

    public ClienteVendaResponseDTO getVendaByCodigo(Long codigoVenda) {
       Venda venda = validateIfVendaExists(codigoVenda);
       return createClienteVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(venda.getCodigo()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public ClienteVendaResponseDTO save(Long codigoCliente, VendaRequestDTO vendaRequestDTO) {
        Cliente cliente = validateIfClienteExists(codigoCliente);
        validateIfProdutoExistsAndUpdateQuantidade(vendaRequestDTO.getItemVendaRequestDTOs());
        Venda venda = saveVenda(cliente, vendaRequestDTO);
        return createClienteVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(venda.getCodigo()));
    }

    private Venda saveVenda(Cliente cliente, VendaRequestDTO vendaRequestDTO) {
        Venda venda =  repository.save(new Venda(vendaRequestDTO.getData(), cliente));
        vendaRequestDTO.getItemVendaRequestDTOs().stream()
                .map(itemVendaDTO -> createItemVenda(itemVendaDTO, venda))
                .forEach(itemVendaRepository::save);
        return venda;
    }

    public ClienteVendaResponseDTO update(Long codigoVenda, Long codigoCliente, VendaRequestDTO vendaRequestDTO) {
        validateIfVendaExists(codigoVenda);
        Cliente cliente = validateIfClienteExists(codigoCliente);
        List<ItemVenda> itensVenda = itemVendaRepository.findByVendaCodigo(codigoVenda);
        validateIfProdutoExistsAndRefillEstoque(itensVenda);
        validateIfProdutoExistsAndUpdateQuantidade(vendaRequestDTO.getItemVendaRequestDTOs());
        itemVendaRepository.deleteAll(itensVenda);
        Venda venda = updateVenda(codigoVenda, cliente, vendaRequestDTO);
        return createClienteVendaResponseDTO(venda, itemVendaRepository.findByVendaCodigo(codigoVenda));
    }

    private Venda updateVenda(Long codigoVenda, Cliente cliente, VendaRequestDTO vendaRequestDTO) {
        Venda venda =  repository.save(new Venda(codigoVenda, vendaRequestDTO.getData(), cliente));
        vendaRequestDTO.getItemVendaRequestDTOs().stream()
                .map(itemVendaDTO -> createItemVenda(itemVendaDTO, venda))
                .forEach(itemVendaRepository::save);
        return venda;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void delete(Long codigoVenda) {
        Venda venda = validateIfVendaExists(codigoVenda);
        List<ItemVenda> itensVenda = itemVendaRepository.findByVendaCodigo(codigoVenda);
        validateIfProdutoExistsAndRefillEstoque(itensVenda);
        itemVendaRepository.deleteAll(itensVenda);
        repository.deleteById(codigoVenda);
    }

    private void validateIfProdutoExistsAndRefillEstoque(List<ItemVenda> itensVenda) {
        itensVenda.forEach(item -> {
            Produto produto = produtoService.validateIfExists(item.getProduto().getCodigo());
            produto.setQuantidade(produto.getQuantidade() + item.getQuantidade());
            produtoService.updateQuantidadeProdutoEmEstoque(produto);
        });
    }

    private void validateIfProdutoExistsAndUpdateQuantidade(List<ItemVendaRequestDTO> itemVendaRequestDTOs) {
        itemVendaRequestDTOs.forEach(item -> {
            Produto produto = produtoService.validateIfExists(item.getCodigoProduto());
            validateIfProdutoTemEstoque(produto, item.getQuantidade());
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoService.updateQuantidadeProdutoEmEstoque(produto);
        });
    }

    private void validateIfProdutoTemEstoque(Produto produto, Integer quantidadeVenda) {
        if (!(produto.getQuantidade() >= quantidadeVenda)) {
            throw new BusinessRuleException(String.format("A quantidade informada para o produto %s não está disponível em estoque.", produto.getDescricao()));
        }
    }

    private Venda validateIfVendaExists(Long codigoVenda) {
        Optional<Venda> venda = repository.findById(codigoVenda);
        if  (venda.isEmpty()) {
            throw new BusinessRuleException(String.format("Venda de código %s não encontrada.", codigoVenda));
        }

        return venda.get();
    }

    private Cliente validateIfClienteExists(Long codigoCliente) {
        Optional<Cliente> cliente = clienteService.getById(codigoCliente);
        if (cliente.isEmpty()) {
            throw new BusinessRuleException(String.format("O cliente de código (%s) informado não existe. ", codigoCliente));
        }
        return cliente.get();
    }
}
