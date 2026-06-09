package com.souza.gestaopessoas2026.controller;

import com.souza.gestaopessoas2026.exceptions.AcessoNaoAutorizadoException;
import com.souza.gestaopessoas2026.exceptions.RequisicaoInvalidaException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteExcecoesController {

    @GetMapping(value = "/int/{v1}")
    public String testarErroInterno(@PathVariable("v1") String v1) {
        if ("erro".equalsIgnoreCase(v1)) {
            throw new RuntimeException("Simulando um erro interno no servidor (500).");
        }
        return "Sucesso na rota /int/" + v1;
    }

    @GetMapping(value = "/bad/{v1}")
    public String testarBadRequest(@PathVariable("v1") String v1) {
        if ("invalido".equalsIgnoreCase(v1)) {
            throw new RequisicaoInvalidaException("Simulando uma requisição inválida (400).");
        }
        return "Sucesso na rota /bad/" + v1;
    }

    @GetMapping(value = "/nao/{v1}")
    public String testarNaoAutorizado(@PathVariable("v1") String v1) {
        if ("bloqueado".equalsIgnoreCase(v1)) {
            throw new AcessoNaoAutorizadoException("Simulando acesso não autorizado (401).");
        }
        return "Sucesso na rota /nao/" + v1;
    }
}