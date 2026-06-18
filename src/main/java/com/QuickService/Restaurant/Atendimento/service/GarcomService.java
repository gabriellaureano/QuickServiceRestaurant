package com.QuickService.Restaurant.Atendimento.service;

import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    private final PedidoRepository pedidoRepository;

    public GarcomService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> findByStatusPronto(){
        return pedidoRepository.findByStatusPedido(StatusPedido.PRONTO);
    }

    public List<PedidoResponse> buscarPedidosProntos(){
        return findByStatusPronto().stream()
                .map(pedido -> new PedidoResponse(
                        pedido.getId(),
                        pedido.getMesa().getId(),
                        pedido.getObservacao(),
                        pedido.getStatusPedido()
                ))
                .toList();
    }
}
