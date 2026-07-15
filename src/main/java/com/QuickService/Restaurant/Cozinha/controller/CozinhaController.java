package com.QuickService.Restaurant.Cozinha.controller;

import com.QuickService.Restaurant.Cozinha.dto.FinalizarPedido;
import com.QuickService.Restaurant.Pedido.dto.PedidoResponse;
import com.QuickService.Restaurant.Cozinha.service.CozinhaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/cozinha")
public class CozinhaController {

    private final CozinhaService cozinhaService;

    public CozinhaController(CozinhaService cozinhaService) {
        this.cozinhaService = cozinhaService;
    }

    @GetMapping("/andamento")
    public List<PedidoResponse> buscarEmAndamento(){
        return cozinhaService.buscarEmAndamento();
    }

    @PutMapping("/finalizar")
    public PedidoResponse finalizarPedido(@RequestBody @Valid FinalizarPedido request){
        return cozinhaService.atualizarStatusPedido(request);
    }

    @GetMapping("/{numeroDoPedido}")
    public List<PedidoResponse> buscarPedidosDaMesa(@PathVariable Long id){
        return cozinhaService.buscarPedidosDaMesa(id);
    }
}
