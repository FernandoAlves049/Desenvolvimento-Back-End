package com.souza.gestaopessoas2026.services;

import com.souza.gestaopessoas2026.model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PessoaService {

    private final AtomicLong counter = new AtomicLong();

    public Pessoa buscarPorId(String id) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(Long.parseLong(id));
        pessoa.setNome("Nome Mockado " + id);
        pessoa.setSobrenome("Sobrenome Mockado");
        pessoa.setEndereco("Endereço Mockado - Brasil");
        pessoa.setGenero("Não Informado");
        return pessoa;
    }

    public List<Pessoa> buscarTodas() {
        List<Pessoa> pessoas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            pessoas.add(buscarPorId(String.valueOf(i)));
        }
        return pessoas;
    }

    public Pessoa criar(Pessoa pessoa) {
        pessoa.setId(counter.incrementAndGet());
        return pessoa;
    }

    public Pessoa atualizar(Pessoa pessoa) {
        return pessoa;
    }

    public void deletar(String id) {
        Long.parseLong(id);
    }
}