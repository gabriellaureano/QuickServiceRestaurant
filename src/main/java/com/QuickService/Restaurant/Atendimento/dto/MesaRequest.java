package com.QuickService.Restaurant.Atendimento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MesaRequest(
        @NotBlank(message = "Numero da mesa invalida ou inexistente")
        @Positive(message = "Numero da mesa invalida ou inexistente")
        Long mesaId,
        @NotBlank(message = "A mesa precisa de um Cliente responsavel")
        String nome
) {
}
