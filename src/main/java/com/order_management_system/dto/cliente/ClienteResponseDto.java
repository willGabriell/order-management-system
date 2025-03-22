package com.order_management_system.dto.cliente;

import com.order_management_system.model.Cliente;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClienteResponseDto {

    private Long id;
    private String nome;
    private String email;
    private String enderecoEntrega;

    public ClienteResponseDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.enderecoEntrega = cliente.getEnderecoEntrega();
    }

    public static List<ClienteResponseDto> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteResponseDto::new).collect(Collectors.toList());
    }

}
