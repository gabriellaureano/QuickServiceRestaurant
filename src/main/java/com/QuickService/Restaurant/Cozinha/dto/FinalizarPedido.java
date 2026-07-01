package com.QuickService.Restaurant.Cozinha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record FinalizarPedido(
        @Positive(message = "Pedido inválido ou não existe.")
        @NotBlank(message = "Id do pedido obrigatorio.")
        Long numeroDoPedido,
        @Positive(message = "Mesa inválida ou não existente")
        @NotBlank(message = "Numero da mesa obrigatorio.")
        Long mesaId
) {
}
