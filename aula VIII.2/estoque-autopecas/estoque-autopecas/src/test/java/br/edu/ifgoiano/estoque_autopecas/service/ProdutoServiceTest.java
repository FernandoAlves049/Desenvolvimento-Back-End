package br.edu.ifgoiano.estoque_autopecas.service;

import br.edu.ifgoiano.estoque_autopecas.model.Produto;
import br.edu.ifgoiano.estoque_autopecas.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        Produto produto = new Produto(null, "Pastilha de Freio", "Marca Bosch", 120.00, 10, 1.5, "Padrão");
        when(repository.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = service.salvar(produto);

        assertNotNull(salvo);
        assertEquals("Pastilha de Freio", salvo.getNome());
        verify(repository, times(1)).save(produto);
    }

    @Test
    void deveLancarExcecaoAoSalvarNomeVazio() {
        Produto produto = new Produto(null, "", "Marca Bosch", 120.00, 10, 1.5, "Padrão");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.salvar(produto));
        assertEquals("O nome da peça é obrigatório e não pode ser vazio.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoSalvarPrecoNegativo() {
        Produto produto = new Produto(null, "Amortecedor", "Traseiro", -10.0, 5, 2.0, "G");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.salvar(produto));
        assertEquals("O preço deve ser maior que zero.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoSalvarQuantidadeNegativa() {
        Produto produto = new Produto(null, "Amortecedor", "Traseiro", 250.00, -1, 2.0, "G");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.salvar(produto));
        assertEquals("A quantidade em estoque não pode ser negativa.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoSalvarPesoNegativo() {
        Produto produto = new Produto(null, "Amortecedor", "Traseiro", 250.00, 5, -0.5, "G");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.salvar(produto));
        assertEquals("O peso deve ser maior que zero.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoSalvarTamanhoVazio() {
        Produto produto = new Produto(null, "Amortecedor", "Traseiro", 250.00, 5, 2.0, "");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.salvar(produto));
        assertEquals("O tamanho é obrigatório e não pode ser vazio.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveListarTodosOsProdutos() {
        List<Produto> produtos = Arrays.asList(
            new Produto(1L, "Pastilha", "Bosch", 100.0, 10, 1.5, "M"),
            new Produto(2L, "Filtro de Óleo", "Fram", 35.0, 20, 0.4, "P")
        );
        when(repository.findAll()).thenReturn(produtos);

        List<Produto> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveDeletarProdutoExistente() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        assertDoesNotThrow(() -> service.deletar(1L));

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarInexistente() {
        when(repository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.deletar(1L));
        assertTrue(exception.getMessage().contains("não encontrado para exclusão"));
        verify(repository, never()).deleteById(anyLong());
    }
}
