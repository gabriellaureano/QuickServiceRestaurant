package com.QuickService.Restaurant.infra.exception;

public class ProdutoNaoEncontradoEx extends RuntimeException{
    public ProdutoNaoEncontradoEx(String message) {
        super(message);
    }
}
