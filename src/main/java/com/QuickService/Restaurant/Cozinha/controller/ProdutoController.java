package com.QuickService.Restaurant.Cozinha.controller;

import com.QuickService.Restaurant.Cozinha.dto.ProdutoRequest;
import com.QuickService.Restaurant.Cozinha.dto.ProdutoResponse;
import com.QuickService.Restaurant.Cozinha.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/criar")
    public ProdutoResponse criarProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
        return produtoService.criarProduto(produtoRequest);
    }

    @GetMapping
    public List<ProdutoResponse> buscarProdutos(){
        return produtoService.buscarProdutos();
    }
}
