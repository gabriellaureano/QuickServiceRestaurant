package com.QuickService.Restaurant.Pedido.dto;

import jakarta.validation.constraints.NotNull;

public record PedidoRequest(
        @NotNull(message = "O numero da mesa é obrigatorio")
        Long mesaId,

        String observacao
) {
}
