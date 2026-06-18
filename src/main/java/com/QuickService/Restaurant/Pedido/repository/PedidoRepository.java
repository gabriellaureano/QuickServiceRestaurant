package com.QuickService.Restaurant.Pedido.repository;

import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByStatusPedido(StatusPedido status);
}
