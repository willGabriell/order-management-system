package com.order_management_system.controller;

import com.order_management_system.dto.cliente.ClienteRequestDto;
import com.order_management_system.dto.cliente.ClienteResponseDto;
import com.order_management_system.model.Cliente;
import com.order_management_system.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastrarCliente(@RequestBody ClienteRequestDto dto) {

        Cliente clienteParaSalvar = new Cliente();
        clienteParaSalvar.setNome(dto.getNome());
        clienteParaSalvar.setEmail(dto.getEmail());
        clienteParaSalvar.setSenha(dto.getSenha());
        clienteParaSalvar.setEnderecoEntrega(dto.getEnderecoEntrega());

        Cliente clienteSalvo = clienteService.cadastrarCliente(clienteParaSalvar);

        ClienteResponseDto responseDto = new ClienteResponseDto(clienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> buscarClientePorId(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente = clienteService.buscarPorId(id);

        ClienteResponseDto responseDto = new ClienteResponseDto(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.status(HttpStatus.OK).body(ClienteResponseDto.converter(clientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequestDto dto) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        Cliente clienteParaAtualizar = new Cliente();
        clienteParaAtualizar.setId(id);
        clienteParaAtualizar.setNome(dto.getNome());
        clienteParaAtualizar.setEmail(dto.getEmail());
        clienteParaAtualizar.setEnderecoEntrega(dto.getEnderecoEntrega());

        Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteParaAtualizar);

        ClienteResponseDto responseDto = new ClienteResponseDto(clienteAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
