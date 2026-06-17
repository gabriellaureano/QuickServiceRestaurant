package com.QuickService.Restaurant.Pedido.exception;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
    LocalDateTime timestamp,
    Integer status,
    String erro,
    List<String> mensagens
) {
}
