package com.souza.gestaopessoas2026.services;

import com.souza.gestaopessoas2026.exceptions.RequisicaoInvalidaException;
import org.springframework.stereotype.Service;

@Service
public class ValidaIdService {

    public void validaId(String id) {
        convertToLong(id);
    }

    public Long convertToLong(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new RequisicaoInvalidaException("O id não pode ser nulo ou vazio.");
        }

        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            throw new RequisicaoInvalidaException("O id informado é inválido: " + id);
        }
    }
}