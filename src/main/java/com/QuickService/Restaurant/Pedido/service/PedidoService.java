package com.QuickService.Restaurant.Pedido.service;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.domain.StatusMesa;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoRequest;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import com.QuickService.Restaurant.infra.exception.MesaSemClienteEx;
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

        if (mesa.getStatusMesa() == StatusMesa.OCUPADA & mesa.getClienteResponsavel() != null){
            pedido.setMesa(mesa);
            pedido.setObservacao(pedidoRequest.observacao());
            mesa.setPedidosAndamento(mesa.getPedidosAndamento() + 1);
            mesaRepository.save(mesa);

            var pedidoSalvo = pedidoRepository.save(pedido);

            return PedidoResponse.fromEntity(pedidoSalvo);
        }else {
            throw new MesaSemClienteEx("Não é possível lançar pedidos: a mesa não possui um cliente responsável.");
        }
    }

    public List<PedidoResponse> buscarPedidos(){
        return pedidoRepository.findAll().stream()
                .map(pedido -> new PedidoResponse(
                        pedido.getId(),
                        pedido.getMesa().getId(),
                        pedido.getMesa().getClienteResponsavel(),
                        pedido.getObservacao(),
                        pedido.getStatusPedido()
                ))
                .toList();
    }
}
