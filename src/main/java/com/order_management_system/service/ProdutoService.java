package com.order_management_system.service;

import com.order_management_system.model.Produto;
import com.order_management_system.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {

        if(id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo");
        }

        return produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    }

    public Produto atualizarProduto(Long id, Produto produto) {
        if (id == null) {
            id = produto.getId();
        }

        Produto produtoSalvo = buscarPorId(id);
        produto.setNome(produtoSalvo.getNome());
        produto.setMarca(produtoSalvo.getMarca());
        produto.setPreco(produtoSalvo.getPreco());
        produto.setQuantidadeEstoque(produtoSalvo.getQuantidadeEstoque());
        produto.setDescricao(produtoSalvo.getDescricao());

        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodosPorId(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }

}
