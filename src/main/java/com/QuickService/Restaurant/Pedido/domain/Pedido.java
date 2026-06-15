package com.QuickService.Restaurant.Pedido.domain;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;
    
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    StatusPedido status_pedido = StatusPedido.ANDAMENTO;

}
