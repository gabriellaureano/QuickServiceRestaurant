package com.QuickService.Restaurant.Atendimento.dto;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.domain.StatusMesa;

public record MesaResponse(
        Long mesaId,
        String clienteResponsavel,
        Integer pedidosAndamento,
        StatusMesa statusMesa
) {
}
