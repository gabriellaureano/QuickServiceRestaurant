package com.QuickService.Restaurant.Pedido.dto;

import com.QuickService.Restaurant.Cozinha.domain.Produto;
import com.QuickService.Restaurant.Cozinha.dto.ProdutoResponse;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        Long numeroDoPedido,
        Long Mesa,
        String clienteResponsavel,
        String observacao,
        StatusPedido statusPedido,
        List<ProdutoResponse> produtos,
        BigDecimal subtotal
) {

    public PedidoResponse {
        subtotal = produtos.stream()
                .map(ProdutoResponse::preco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static PedidoResponse fromEntity(Pedido pedido){

        List<ProdutoResponse> produtosDto = pedido.getProdutos().stream()
                .map(p -> new ProdutoResponse(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(),p.getCategoria(),p.getAtivo()))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getMesa().getId(),
                pedido.getMesa().getClienteResponsavel(),
                pedido.getObservacao(),
                pedido.getStatusPedido(),
                produtosDto,
                null
        );
    }
}
