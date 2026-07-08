package com.QuickService.Restaurant.Atendimento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_historico_fechamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoFechamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mesaId;

    private String clienteResponsavel;

    private BigDecimal valorTotal;

    private LocalDateTime dataFechamento;
}
