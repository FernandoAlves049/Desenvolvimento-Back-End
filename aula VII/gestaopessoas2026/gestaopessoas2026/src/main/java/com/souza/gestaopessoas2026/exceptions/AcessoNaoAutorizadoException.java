package com.souza.gestaopessoas2026.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AcessoNaoAutorizadoException extends RuntimeException {
    public AcessoNaoAutorizadoException(String ex) {
        super(ex);
    }
}