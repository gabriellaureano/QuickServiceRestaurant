package com.QuickService.Restaurant.Atendimento.controller;

import com.QuickService.Restaurant.Atendimento.dto.MesaRequest;
import com.QuickService.Restaurant.Atendimento.dto.MesaResponse;
import com.QuickService.Restaurant.Atendimento.service.GarcomService;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/abertura")
    public MesaResponse abrirMesa(@RequestBody MesaRequest mesaRequest){
        return garcomService.addClienteResponsavel(mesaRequest);
    }

    @GetMapping
    public List<MesaResponse> buscarMesas(){
        return garcomService.buscarMesas();
    }

    @PutMapping("/mesa/{mesaId}/finalizar")
    public ResponseEntity finalizarMesa(@PathVariable Long mesaId){
        return garcomService.finalizarMesa(mesaId);
    }
}
