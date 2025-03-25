package com.order_management_system.dto.pedido;

import com.order_management_system.enums.StatusPedido;
import com.order_management_system.model.Pedido;
import com.order_management_system.model.Produto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PedidoResponseDto {

    private Long id;
    private Long clienteId;
    private String clienteNome;
    private Date dataPedido;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private List<Produto> itensPedido;

    public PedidoResponseDto(Pedido pedido) {
        this.id = pedido.getId();
        this.clienteId = pedido.getCliente().getId();
        this.clienteNome = pedido.getCliente().getNome();
        this.valorTotal = pedido.getValorTotal();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.itensPedido = pedido.getItensPedido();
    }

}
