package com.QuickService.Restaurant.Atendimento.controller;

import com.QuickService.Restaurant.Atendimento.service.GarcomService;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/atendimento")
public class GarcomController {

    private final GarcomService garcomService;

    public GarcomController(GarcomService garcomService) {
        this.garcomService = garcomService;
    }

    @GetMapping("/pedidos/prontos")
    public List<PedidoResponse> buscarPedidosProntos(){
        return garcomService.buscarPedidosProntos();
    }
}
