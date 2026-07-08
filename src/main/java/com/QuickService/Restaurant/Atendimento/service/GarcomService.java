package com.QuickService.Restaurant.Atendimento.service;

import com.QuickService.Restaurant.Atendimento.domain.HistoricoFechamento;
import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.domain.StatusMesa;
import com.QuickService.Restaurant.Atendimento.dto.FechamentoResponse;
import com.QuickService.Restaurant.Atendimento.dto.ItemFechamentoDTO;
import com.QuickService.Restaurant.Atendimento.dto.MesaRequest;
import com.QuickService.Restaurant.Atendimento.dto.MesaResponse;
import com.QuickService.Restaurant.Atendimento.repository.HistoricoFechamentoRepository;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Cozinha.domain.Produto;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.domain.StatusPedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.infra.exception.MesaOcupadaEx;
import com.QuickService.Restaurant.infra.exception.PedidosEmAndamentoEx;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GarcomService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final HistoricoFechamentoRepository historicoFechamentoRepository;

    public GarcomService(PedidoRepository pedidoRepository, MesaRepository mesaRepository, HistoricoFechamentoRepository historicoFechamentoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.historicoFechamentoRepository = historicoFechamentoRepository;
    }

    public List<Pedido> findByStatusPronto(){
        return pedidoRepository.findByStatusPedido(StatusPedido.PRONTO);
    }

    public List<PedidoResponse> buscarPedidosProntos(){
        return findByStatusPronto().stream()
                .map(PedidoResponse::fromEntity)
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
                    mesa.getPedidosAndamento(),
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
                        mesa.getPedidosAndamento(),
                        mesa.getStatusMesa()
                ))
                .toList();
    }

    public ResponseEntity fecharConta(Long mesaId){
        Mesa mesaAtual = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new MesaNaoEncontradaEx("Mesa não encontrada"));

        if (mesaAtual.getPedidosAndamento() != 0){
            throw new PedidosEmAndamentoEx("Não é possivel finalizar a mesa com pedidos em andamento na fila.");
        }

        List<Pedido> pedidos = pedidoRepository.findByMesa(mesaAtual);

        BigDecimal valorTotal = pedidos.stream()
                .flatMap(pedido -> pedido.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        HistoricoFechamento historicoFechamento = new HistoricoFechamento(
        );
        historicoFechamento.setMesaId(mesaId);
        historicoFechamento.setClienteResponsavel(mesaAtual.getClienteResponsavel());
        historicoFechamento.setValorTotal(valorTotal);
        historicoFechamento.setDataFechamento(LocalDateTime.now());

        List<ItemFechamentoDTO> itens = pedidos.stream()
                .flatMap(pedido -> pedido.getProdutos().stream())
                .map(produto -> new ItemFechamentoDTO(produto.getNome(),produto.getPreco()))
                .toList();

        FechamentoResponse fechamento = new FechamentoResponse(
                mesaId,
                mesaAtual.getClienteResponsavel(),
                itens,
                valorTotal,
                LocalDateTime.now()
        );

        HistoricoFechamento historico = new HistoricoFechamento(
        );
        historico.setMesaId(mesaId);
        historico.setClienteResponsavel(mesaAtual.getClienteResponsavel());
        historico.setValorTotal(valorTotal);
        historico.setDataFechamento(LocalDateTime.now());
        historicoFechamentoRepository.save(historico);

        mesaAtual.setClienteResponsavel(null);
        mesaAtual.setStatusMesa(StatusMesa.LIVRE);
        mesaRepository.save(mesaAtual);
        pedidoRepository.deleteByMesaId(mesaAtual.getId());

        return ResponseEntity.status(HttpStatus.OK).body(fechamento);
    }

    public List<HistoricoFechamento> buscarHistoricoFechamento(){
        return historicoFechamentoRepository.findAll();
    }
}
