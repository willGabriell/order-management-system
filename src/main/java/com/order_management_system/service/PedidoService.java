package com.order_management_system.service;

import com.order_management_system.dto.pedido.PedidoRequestDto;
import com.order_management_system.enums.StatusPedido;
import com.order_management_system.model.Pedido;
import com.order_management_system.repository.PedidoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Pedido criarPedido(@NotNull PedidoRequestDto dto) {
        Pedido pedidoNovo = new Pedido();
        pedidoNovo.setCliente(clienteService.buscarPorId(dto.getClienteId()));
        pedidoNovo.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedidoNovo.setItensPedido(produtoService.buscarTodosPorId(dto.getItensPedidoId()));

        return pedidoRepository.save(pedidoNovo);
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }

    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido atualizarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
