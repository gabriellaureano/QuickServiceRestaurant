package com.QuickService.Restaurant.Cozinha.dto;

import com.QuickService.Restaurant.Cozinha.domain.CategoriaProduto;
import com.QuickService.Restaurant.Cozinha.domain.Produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long produtoId,
        String produtoNome,
        String descricao,
        BigDecimal preco,
        CategoriaProduto categoria,
        Boolean ativo
) {
    public static ProdutoResponse fromEntity(Produto produto){
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getAtivo()
        );
    }
}
