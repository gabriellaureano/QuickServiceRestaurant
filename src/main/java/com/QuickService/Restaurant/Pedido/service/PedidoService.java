package com.QuickService.Restaurant.Pedido.service;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoRequest;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    public PedidoService(PedidoRepository pedidoRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
    }

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest){
        Pedido pedido = new Pedido();

        Mesa mesa = mesaRepository.findById(pedidoRequest.mesaId())
                        .orElseThrow(() -> new MesaNaoEncontradaEx("Mesa não encontrada"));

        pedido.setMesa(mesa);
        pedido.setObservacao(pedidoRequest.observacao());

        var pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoResponse.fromEntity(pedidoSalvo);
    }

    public List<PedidoResponse> buscarPedidos(){
        return pedidoRepository.findAll().stream()
                .map(pedido -> new PedidoResponse(
                        pedido.getId(),
                        pedido.getMesa().getId(),
                        pedido.getObservacao(),
                        pedido.getStatusPedido()
                ))
                .toList();
    }
}
