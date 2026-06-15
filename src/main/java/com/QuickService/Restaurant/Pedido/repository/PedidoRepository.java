package com.QuickService.Restaurant.Pedido.repository;

import com.QuickService.Restaurant.Pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
