package com.QuickService.Restaurant.Pedido.service;

import com.QuickService.Restaurant.Atendimento.domain.Mesa;
import com.QuickService.Restaurant.Atendimento.domain.StatusMesa;
import com.QuickService.Restaurant.Atendimento.repository.MesaRepository;
import com.QuickService.Restaurant.Cozinha.domain.Produto;
import com.QuickService.Restaurant.Cozinha.repository.ProdutoRepository;
import com.QuickService.Restaurant.Pedido.domain.Pedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoRequest;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.infra.exception.MesaNaoEncontradaEx;
import com.QuickService.Restaurant.Pedido.repository.PedidoRepository;
import com.QuickService.Restaurant.infra.exception.MesaSemClienteEx;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, MesaRepository mesaRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoResponse criarPedido(PedidoRequest pedidoRequest){
        Pedido pedido = new Pedido();

        Mesa mesa = mesaRepository.findById(pedidoRequest.mesaId())
                        .orElseThrow(() -> new MesaNaoEncontradaEx("Mesa não encontrada"));

        if (mesa.getStatusMesa() == StatusMesa.OCUPADA & mesa.getClienteResponsavel() != null){
            pedido.setMesa(mesa);
            pedido.setObservacao(pedidoRequest.observacao());

            List<Produto> listaProdutos = new ArrayList<>();

            List<Produto> listaProdutosDisponiveis = produtoRepository.findAll();

            Map<Long, Produto> mapaProdutos = listaProdutosDisponiveis.stream()
                            .collect(Collectors.toMap(Produto::getId,produto -> produto));

            for (Long id : pedidoRequest.produtosIds()){
                Produto produto = mapaProdutos.get(id);
                if (produto == null){
                    new RuntimeException("Produto não encontrado");
                }
                listaProdutos.add(produto);
            }

            pedido.setProdutos(listaProdutos);
            mesa.setPedidosAndamento(mesa.getPedidosAndamento() + 1);

            var pedidoSalvo = pedidoRepository.save(pedido);
            mesaRepository.save(mesa);

            return PedidoResponse.fromEntity(pedidoSalvo);
        }else {
            throw new MesaSemClienteEx("Não é possível lançar pedidos: a mesa não possui um cliente responsável.");
        }
    }

    public List<PedidoResponse> buscarPedidos(){
        return pedidoRepository.findAll().stream()
                .map(PedidoResponse::fromEntity)
                .toList();
    }
}
