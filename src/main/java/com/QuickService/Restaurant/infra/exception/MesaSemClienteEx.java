package com.QuickService.Restaurant.infra.exception;

public class MesaSemClienteEx extends RuntimeException{
    public MesaSemClienteEx(String message) {
        super(message);
    }
}
