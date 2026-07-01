package com.QuickService.Restaurant.Cozinha.service;

import com.QuickService.Restaurant.Cozinha.domain.Produto;
import com.QuickService.Restaurant.Cozinha.dto.ProdutoRequest;
import com.QuickService.Restaurant.Cozinha.dto.ProdutoResponse;
import com.QuickService.Restaurant.Cozinha.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoResponse criarProduto(ProdutoRequest request){
        Produto produto = ProdutoRequest.toEntity(request);

        produtoRepository.save(produto);

        return ProdutoResponse.fromEntity(produto);
    }

    public List<ProdutoResponse> buscarProdutos(){
        return produtoRepository.findAll().stream()
                .map(ProdutoResponse::fromEntity)
                .toList();
    }
}
