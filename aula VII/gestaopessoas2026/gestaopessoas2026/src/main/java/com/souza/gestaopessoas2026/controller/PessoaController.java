package com.souza.gestaopessoas2026.controller;

import com.souza.gestaopessoas2026.model.Pessoa;
import com.souza.gestaopessoas2026.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa buscarPorId(@PathVariable("id") String id) {
        return pessoaService.buscarPorId(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> buscarTodas() {
        return pessoaService.buscarTodas();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaService.criar(pessoa);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return pessoaService.atualizar(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable("id") String id) {
        pessoaService.deletar(id);
    }
}