package com.order_management_system.dto.pedido;

import com.order_management_system.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDto {

    @NotNull(message = "O id do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "A lista de itens do pedido é obrigatória")
    private List<Long> itensPedidoId;

}
