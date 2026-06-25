package com.QuickService.Restaurant.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MesaNaoEncontradaEx.class)
    public ResponseEntity MesaNaoEncontrada(MesaNaoEncontradaEx erro){
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Mesa Não Encontrada",
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(MethodArgumentNotValidException erro){
        List<String> mensagens = erro.getBindingResult().getFieldErrors()
                .stream()
                .map(ex -> ex.getField()+ ": "+ex.getDefaultMessage())
                .toList();

        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagens
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(PedidoNaoEncontradoEx.class)
    public ResponseEntity tratarPedidoNaoEncontrado(PedidoNaoEncontradoEx erro){
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Pedido Não Encontrado",
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(MesaOcupadaEx.class)
    public ResponseEntity tratarMesaOcupada(MesaOcupadaEx erro){
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Mesa Ocupada",
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(MesaSemClienteEx.class)
    public ResponseEntity tratarMesaSemCliente(MesaSemClienteEx erro){
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Mesa Sem Cliente",
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(PedidosEmAndamentoEx.class)
    public ResponseEntity tratarPedidosEmAndamento(PedidosEmAndamentoEx erro){
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Pedidos em andamento",
                List.of(erro.getMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

}
