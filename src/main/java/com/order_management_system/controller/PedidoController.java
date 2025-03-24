package com.order_management_system.controller;

import com.order_management_system.dto.pedido.PedidoRequestDto;
import com.order_management_system.dto.pedido.PedidoResponseDto;
import com.order_management_system.model.Pedido;
import com.order_management_system.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> criarPedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        Pedido pedido = pedidoService.criarPedido(pedidoRequestDto);

        PedidoResponseDto dtoResposta = new PedidoResponseDto(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResposta);
    }

    public ResponseEntity<PedidoResponseDto> buscarPorId() {
        return null;
    }

    public List<ResponseEntity<PedidoResponseDto>> buscarTodos() {
        return null;
    }

    public ResponseEntity<PedidoResponseDto> atualizarStatusPedido() {
        return null;
    }

}
