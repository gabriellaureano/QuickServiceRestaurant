package com.QuickService.Restaurant.Cozinha.dto;

import com.QuickService.Restaurant.Cozinha.domain.CategoriaProduto;
import com.QuickService.Restaurant.Cozinha.domain.Produto;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRequest(
        @NotNull(message = "O produto precisa de um nome")
        String nome,
        String descricao,
        @Positive(message = "O produto precisa de um valor maior que zero")
        BigDecimal preco,
        @NotNull(message = "O produto precisa de uma categoria")
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
