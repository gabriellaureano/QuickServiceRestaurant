package com.QuickService.Restaurant.Pedido.repository;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    List<Pedido> findByStatusPedido(StatusPedido status);
    List<Pedido> findByMesa(Mesa mesa);

    @Modifying
    @Transactional
    void deleteByMesaId(Long mesaId);
}
