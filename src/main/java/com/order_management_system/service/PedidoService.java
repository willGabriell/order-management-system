package com.order_management_system.service;

import com.order_management_system.dto.itemPedido.ItemPedidoDto;
import com.order_management_system.dto.pedido.PedidoRequestDto;
import com.order_management_system.enums.StatusPedido;
import com.order_management_system.model.Pedido;
import com.order_management_system.model.Produto;
import com.order_management_system.repository.PedidoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Pedido criarPedido(@NotNull PedidoRequestDto dto) {
        Pedido pedidoNovo = new Pedido();
        BigDecimal valorTotal = BigDecimal.ZERO;

        pedidoNovo.setCliente(clienteService.buscarPorId(dto.getClienteId()));
        pedidoNovo.setStatus(StatusPedido.EM_PROCESSAMENTO);

        List<ItemPedidoDto> itensPedidoDto = dto.getItensPedido();
        List<Produto> itensPedido = new ArrayList<>();

        for (ItemPedidoDto itemDto : itensPedidoDto) {
            Produto p = produtoService.buscarPorId(itemDto.getProdutoId());

            if (p.getQuantidadeEstoque() < itemDto.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para o produto " + p.getNome());
            }

            valorTotal = valorTotal.add(p.getPreco().multiply(new BigDecimal(itemDto.getQuantidade())));
            p.setQuantidadeEstoque(p.getQuantidadeEstoque() - itemDto.getQuantidade());
            produtoService.atualizarProduto(p.getId(), p);
            itensPedido.add(p);
        }

        pedidoNovo.setValorTotal(valorTotal);
        pedidoNovo.setItensPedido(itensPedido);
        return pedidoRepository.save(pedidoNovo);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido atualizarStatusPedido(Pedido pedido, @NotNull StatusPedido status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
}
