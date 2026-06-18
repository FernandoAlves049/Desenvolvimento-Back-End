package br.edu.ifgoiano.estoque_autopecas.repository;

import br.edu.ifgoiano.estoque_autopecas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Método customizado para buscar autopeças por nome, ignorando maiúsculas e minúsculas
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
