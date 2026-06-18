package com.souza.gestaopessoas2026.controller;

import com.souza.gestaopessoas2026.model.Pessoa;
import com.souza.gestaopessoas2026.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/{id}")
    public Pessoa buscarPorId(@PathVariable("id") String id) {
        return pessoaService.findByID(id);
    }

    @GetMapping
    public List<Pessoa> buscarTodas() {
        return pessoaService.findAllPersons();
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaService.create(pessoa);
    }

    @PutMapping
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        return pessoaService.update(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable("id") String id) {
        pessoaService.delete(id);
    }
}