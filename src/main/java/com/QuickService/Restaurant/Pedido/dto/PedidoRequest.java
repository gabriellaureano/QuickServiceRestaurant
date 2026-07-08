package com.QuickService.Restaurant.Pedido.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequest(
        @NotNull(message = "O numero da mesa é obrigatorio")
        Long mesaId,
        List<Long> produtosIds,
        String observacao
) {
}
