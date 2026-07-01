package com.QuickService.Restaurant.Cozinha.dto;

import com.QuickService.Restaurant.Cozinha.domain.CategoriaProduto;
import com.QuickService.Restaurant.Cozinha.domain.Produto;

import java.math.BigDecimal;

public record ProdutoRequest(
        String nome,
        String descricao,
        BigDecimal preco,
        CategoriaProduto categoria
) {
     public static Produto toEntity(ProdutoRequest produtoRequest){
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setDescricao(produtoRequest.descricao());
        produto.setPreco(produtoRequest.preco());
        produto.setCategoria(produtoRequest.categoria());

        return produto;
    }
}
