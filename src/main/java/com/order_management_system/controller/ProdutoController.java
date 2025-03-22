package com.order_management_system.controller;

import com.order_management_system.dto.produto.ProdutoRequestDto;
import com.order_management_system.dto.produto.ProdutoResponseDto;
import com.order_management_system.model.Produto;
import com.order_management_system.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastrarProduto(@RequestBody ProdutoRequestDto dto) {

        Produto produtoCadastrado = new Produto();
        produtoCadastrado.setNome(dto.getNome());
        produtoCadastrado.setDescricao(dto.getDescricao());
        produtoCadastrado.setMarca(dto.getMarca());
        produtoCadastrado.setPreco(dto.getPreco());
        produtoCadastrado.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Produto produtoSalvo = produtoService.cadastrarProduto(produtoCadastrado);

        ProdutoResponseDto responseDto = new ProdutoResponseDto(produtoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> buscarProdutoPorId(@PathVariable Long id) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        Produto produto = produtoService.buscarPorId(id);

        ProdutoResponseDto responseDto = new ProdutoResponseDto(produto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoResponseDto.converter(produtoService.listarProdutos()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDto dto) {

        if(id == null) {
            return ResponseEntity.badRequest().build();
        }

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setMarca(dto.getMarca());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);

        ProdutoResponseDto responseDto = new ProdutoResponseDto(produtoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
