package com.QuickService.Restaurant.Pedido.controller;

import com.QuickService.Restaurant.Pedido.dto.PedidoRequest;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Pedido.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criarPedido(@RequestBody @Valid PedidoRequest pedidoRequest){
        return pedidoService.criarPedido(pedidoRequest);
    }

    @GetMapping("/pedido")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponse> buscarPedidos(){
        return pedidoService.buscarPedidos();
    }
}
