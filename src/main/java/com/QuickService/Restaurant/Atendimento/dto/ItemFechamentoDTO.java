package com.QuickService.Restaurant.Atendimento.dto;

import java.math.BigDecimal;

public record ItemFechamentoDTO(
        String produtoNome,
        BigDecimal precoUnitario
) {
}
