package com.QuickService.Restaurant.infra.exception;

public class PedidoNaoEncontradoEx extends RuntimeException{
    public PedidoNaoEncontradoEx(String message){
        super(message);
    }
}
