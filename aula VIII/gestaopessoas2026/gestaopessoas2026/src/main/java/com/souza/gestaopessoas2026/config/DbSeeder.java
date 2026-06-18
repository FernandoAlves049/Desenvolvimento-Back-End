package com.souza.gestaopessoas2026.config;

import com.souza.gestaopessoas2026.model.Pessoa;
import com.souza.gestaopessoas2026.repositories.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DbSeeder {

    @Bean
    CommandLineRunner initDatabase(PessoaRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Pessoa p1 = new Pessoa();
                p1.setNome("Maria");
                p1.setSobrenome("Silva");
                p1.setEndereco("Rua das Flores, 123 - São Paulo");
                p1.setGenero("Feminino");

                Pessoa p2 = new Pessoa();
                p2.setNome("João");
                p2.setSobrenome("Santos");
                p2.setEndereco("Avenida Central, 456 - Rio de Janeiro");
                p2.setGenero("Masculino");

                Pessoa p3 = new Pessoa();
                p3.setNome("Ana");
                p3.setSobrenome("Oliveira");
                p3.setEndereco("Rua do Sol, 789 - Belo Horizonte");
                p3.setGenero("Feminino");

                Pessoa p4 = new Pessoa();
                p4.setNome("Carlos");
                p4.setSobrenome("Souza");
                p4.setEndereco("Praça da Sé, 10 - Salvador");
                p4.setGenero("Masculino");

                repository.saveAll(Arrays.asList(p1, p2, p3, p4));
                System.out.println("Banco de dados populado com 4 pessoas com sucesso!");
            } else {
                System.out.println("Banco de dados já contém registros. Seed ignorado.");
            }
        };
    }
}
