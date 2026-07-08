package com.QuickService.Restaurant.Pedido.domain;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Cozinha.domain.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    StatusPedido statusPedido = StatusPedido.ANDAMENTO;

}
