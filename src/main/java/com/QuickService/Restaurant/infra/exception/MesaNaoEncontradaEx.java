package com.QuickService.Restaurant.infra.exception;

public class MesaNaoEncontradaEx extends RuntimeException{
    public MesaNaoEncontradaEx(String message) {
        super(message);
    }
}
