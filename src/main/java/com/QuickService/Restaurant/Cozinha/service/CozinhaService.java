package com.QuickService.Restaurant.Cozinha.service;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Cozinha.dto.FinalizarPedido;
import com.QuickService.Restaurant.Cozinha.dto.ProdutoResponse;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.infra.exception.PedidoNaoEncontradoEx;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    public CozinhaService(PedidoRepository pedidoRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
    }

    public List<Pedido> findByStatusAndamento(){
        return pedidoRepository.findByStatusPedido(StatusPedido.ANDAMENTO);
    }


    public List<PedidoResponse> buscarEmAndamento(){
        return findByStatusAndamento().stream()
                .map(PedidoResponse::fromEntity)
                .toList();
    }

    public PedidoResponse atualizarStatusPedido(FinalizarPedido finalizarPedido){
        Pedido pedidoAtual = pedidoRepository.findById(finalizarPedido.numeroDoPedido())
                .orElseThrow(()-> new PedidoNaoEncontradoEx("Pedido Não Encontrado"));

        if (!pedidoAtual.getMesa().getId().equals(finalizarPedido.mesaId())){
            throw new MesaNaoEncontradaEx("MesaId não corresponde a nenhuma mesa existente.");
        }


        pedidoAtual.setStatusPedido(StatusPedido.PRONTO);
        pedidoAtual.getMesa().setPedidosAndamento(pedidoAtual.getMesa().getPedidosAndamento() - 1);

        mesaRepository.save(pedidoAtual.getMesa());
        Pedido pedidoAtualizado = pedidoRepository.save(pedidoAtual);

        return new PedidoResponse(
                pedidoAtualizado.getId(),
                pedidoAtualizado.getMesa().getId(),
                pedidoAtualizado.getMesa().getClienteResponsavel(),
                pedidoAtualizado.getObservacao(),
                pedidoAtualizado.getStatusPedido(),
                pedidoAtualizado.getProdutos().stream().map(ProdutoResponse::fromEntity).toList(),
                null
        );

    }

    public List<PedidoResponse> buscarPedidosDaMesa(Long id){
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new MesaNaoEncontradaEx("Mesa não encontrada"));

        return pedidoRepository.findByMesa(mesa).stream()
                .map(PedidoResponse::fromEntity)
                .toList();

    }




}
