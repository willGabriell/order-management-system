package com.order_management_system.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDto {

    @NotBlank(message = "Não é possível cadastrar um cliente sem nome")
    private String nome;

    @NotBlank(message = "Não é possível cadastrar um cliente sem email")
    private String email;

    @NotBlank(message = "Não é possível cadastrar um cliente sem senha")
    private String senha;

    @NotBlank(message = "Não é possível cadastrar um cliente sem endereço de entrega")
    private String enderecoEntrega;
}
