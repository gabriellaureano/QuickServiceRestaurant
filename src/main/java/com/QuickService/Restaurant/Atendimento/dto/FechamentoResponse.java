package com.QuickService.Restaurant.Atendimento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record FechamentoResponse(
        Long mesaId,
        String clienteResponsavel,
        List<ItemFechamentoDTO> itensConsumidos,
        BigDecimal valorTotal,
        LocalDateTime dataFechamento
) {
}
