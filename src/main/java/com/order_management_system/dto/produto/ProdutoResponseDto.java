package com.order_management_system.dto.produto;

import com.order_management_system.model.Produto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProdutoResponseDto {

    private Long id;
    private String nome;
    private String marca;
    private String descricao;
    private int quantidadeEstoque;
    private double preco;

    public ProdutoResponseDto(Produto p) {
        this.id = p.getId();
        this.nome = p.getNome();
        this.marca = p.getMarca();
        this.descricao = p.getDescricao();
        this.quantidadeEstoque = p.getQuantidadeEstoque();
        this.preco = p.getPreco().doubleValue();
    }

    public static List<ProdutoResponseDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoResponseDto::new).collect(Collectors.toList());
    }

}
