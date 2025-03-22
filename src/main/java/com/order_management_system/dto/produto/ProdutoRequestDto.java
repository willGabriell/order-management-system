package com.order_management_system.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDto {

    @NotBlank(message = "Não é possivel cadastrar um produto sem nome")
    private String nome;

    @NotBlank(message = "Não é possivel cadastrar um produto sem marca")
    private String marca;

    @NotBlank(message = "Não é possivel cadastrar um produto sem descrição")
    private String descricao;

    @NotNull(message = "Não é possivel cadastrar um produto sem quantidade em estoque")
    private int quantidadeEstoque;

    @NotNull(message = "Não é possivel cadastrar um produto sem preço")
    private BigDecimal preco;

}
