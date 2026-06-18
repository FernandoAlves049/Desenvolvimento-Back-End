package com.souza.gestaopessoas2026.services;

import com.souza.gestaopessoas2026.exceptions.ResourceNotFoundException;
import com.souza.gestaopessoas2026.model.Pessoa;
import com.souza.gestaopessoas2026.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private ValidaIdService validaIdService;

    public Pessoa findByID(String id) {
        Long pessoaId = validaIdService.convertToLong(id);
        return repository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a pessoa com id: " + id));
    }

    public List<Pessoa> findAllPersons() {
        return repository.findAll();
    }

    public Pessoa create(Pessoa pessoa) {
        pessoa.setId(null);
        return repository.save(pessoa);
    }

    public Pessoa update(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            throw new ResourceNotFoundException("Não foi possível atualizar a pessoa sem id.");
        }

        Long pessoaId = validaIdService.convertToLong(String.valueOf(pessoa.getId()));
        Pessoa entity = repository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a pessoa com id: " + pessoaId));

        entity.setNome(pessoa.getNome());
        entity.setSobrenome(pessoa.getSobrenome());
        entity.setEndereco(pessoa.getEndereco());
        entity.setGenero(pessoa.getGenero());

        return repository.save(entity);
    }

    public void delete(String id) {
        Long pessoaId = validaIdService.convertToLong(id);
        Pessoa entity = repository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar a pessoa com id: " + id));

        repository.delete(entity);
    }
}