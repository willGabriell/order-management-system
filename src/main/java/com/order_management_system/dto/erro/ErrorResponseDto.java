package com.order_management_system.dto.erro;

import lombok.Data;

@Data
public class ErrorResponseDto {

    private String mensagem;

    public ErrorResponseDto(String mensagem) {
        this.mensagem = mensagem;
    }
}
