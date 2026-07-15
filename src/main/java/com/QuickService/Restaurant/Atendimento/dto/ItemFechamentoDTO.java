package com.QuickService.Restaurant.Atendimento.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ItemFechamentoDTO(
        String produtoNome,
        BigDecimal precoUnitario
) {
}
