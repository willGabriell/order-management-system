package com.order_management_system.controller;

import com.order_management_system.dto.pedido.PedidoRequestDto;
import com.order_management_system.dto.pedido.PedidoResponseDto;
import com.order_management_system.dto.status.StatusDto;
import com.order_management_system.enums.StatusPedido;
import com.order_management_system.model.Pedido;
import com.order_management_system.service.PedidoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDto> buscarPorId(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        PedidoResponseDto dtoResposta = new PedidoResponseDto(pedido);

        return ResponseEntity.ok(dtoResposta);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> buscarTodos() {
        List<Pedido> pedidos = pedidoService.buscarTodos();
        List<PedidoResponseDto> pedidosDto = PedidoResponseDto.toList(pedidos);

        return ResponseEntity.ok(pedidosDto);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDto> atualizarStatusPedido(@PathVariable Long id, @RequestBody @NotNull StatusDto statusDto) {
        Pedido p = pedidoService.buscarPorId(id);
        StatusPedido status = statusDto.getStatus();
        Pedido pedidoAtualizado = pedidoService.atualizarStatusPedido(p, status);
        PedidoResponseDto dtoResposta = new PedidoResponseDto(pedidoAtualizado);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResposta);
    }

}
