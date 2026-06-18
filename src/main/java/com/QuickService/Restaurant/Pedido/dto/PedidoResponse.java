package com.QuickService.Restaurant.Pedido.dto;

import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;

public record PedidoResponse(
        Long id,
        Long Mesa,
        String observacao,
        StatusPedido statusPedido
) {

    public static PedidoResponse fromEntity(Pedido pedido){
        return new PedidoResponse(
                pedido.getId(),
                pedido.getMesa().getId(),
                pedido.getObservacao(),
                pedido.getStatusPedido()
        );
    }
}
