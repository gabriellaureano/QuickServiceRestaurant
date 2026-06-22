package com.QuickService.Restaurant.Atendimento.service;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.domain.StatusMesa;
import com.QuickService.Restaurant.Atendimento.dto.MesaRequest;
import com.QuickService.Restaurant.Atendimento.dto.MesaResponse;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.infra.exception.MesaOcupadaEx;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarcomService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    public GarcomService(PedidoRepository pedidoRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
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

    public MesaResponse addClienteResponsavel(MesaRequest mesaRequest){
        Mesa mesa = mesaRepository.findById(mesaRequest.mesaId())
                .orElseThrow(() -> new MesaNaoEncontradaEx("Mesa não encontrada."));

        if (mesa.getClienteResponsavel() == null){
            mesa.setClienteResponsavel(mesaRequest.nome());
            mesa.setStatusMesa(StatusMesa.OCUPADA);
            mesaRepository.save(mesa);
            return new MesaResponse(
                    mesa.getId(),
                    mesa.getClienteResponsavel(),
                    mesa.getStatusMesa()
            );
        }else {
            throw new MesaOcupadaEx("Mesa ocupada no momento.");
        }

    }

    public List<MesaResponse> buscarMesas(){
        return mesaRepository.findAll().stream()
                .map(mesa -> new MesaResponse(
                        mesa.getId(),
                        mesa.getClienteResponsavel(),
                        mesa.getStatusMesa()
                ))
                .toList();
    }
}
