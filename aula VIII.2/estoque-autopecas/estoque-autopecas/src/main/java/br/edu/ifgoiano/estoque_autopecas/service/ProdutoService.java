package br.edu.ifgoiano.estoque_autopecas.service;

import br.edu.ifgoiano.estoque_autopecas.model.Produto;
import br.edu.ifgoiano.estoque_autopecas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public List<Produto> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return listarTodos();
        }
        return repository.findByNomeContainingIgnoreCase(nome.trim());
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Produto salvar(Produto produto) {
        validarProduto(produto);
        return repository.save(produto);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }

    private void validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da peça é obrigatório e não pode ser vazio.");
        }
        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        if (produto.getPeso() == null || produto.getPeso() <= 0) {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }
        if (produto.getTamanho() == null || produto.getTamanho().trim().isEmpty()) {
            throw new IllegalArgumentException("O tamanho é obrigatório e não pode ser vazio.");
        }
    }
}
