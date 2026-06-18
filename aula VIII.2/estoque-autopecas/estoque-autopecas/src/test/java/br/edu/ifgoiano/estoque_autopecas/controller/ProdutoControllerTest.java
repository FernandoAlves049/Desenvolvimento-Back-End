package br.edu.ifgoiano.estoque_autopecas.controller;

import br.edu.ifgoiano.estoque_autopecas.model.Produto;
import br.edu.ifgoiano.estoque_autopecas.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProdutoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProdutoService service;

    @InjectMocks
    private ProdutoController produtoController;

    private MockMvc homeMockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
        homeMockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    void deveRedirecionarRaizParaProdutos() throws Exception {
        homeMockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"));
    }

    @Test
    void deveExibirListaDeProdutos() throws Exception {
        Produto p = new Produto(1L, "Pastilha de Freio", "Marca Bosch", 120.0, 10, 1.5, "Padrão");
        when(service.listarTodos()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/lista"))
                .andExpect(model().attributeExists("produtos"))
                .andExpect(model().attribute("totalItens", 10))
                .andExpect(model().attribute("valorTotal", 1200.0))
                .andExpect(model().attribute("totalPecasUnicas", 1L));
    }

    @Test
    void deveFiltrarProdutosPorNome() throws Exception {
        Produto p = new Produto(1L, "Pastilha de Freio", "Marca Bosch", 120.0, 10, 1.5, "Padrão");
        when(service.buscarPorNome("Pastilha")).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/produtos").param("search", "Pastilha"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/lista"))
                .andExpect(model().attributeExists("produtos"))
                .andExpect(model().attribute("search", "Pastilha"));
    }

    @Test
    void deveExibirFormularioNovo() throws Exception {
        mockMvc.perform(get("/produtos/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().attributeExists("produto"))
                .andExpect(model().attribute("titulo", "Cadastrar Nova Peça"));
    }

    @Test
    void deveSalvarComSucessoERedirecionar() throws Exception {
        Produto p = new Produto(null, "Pastilha de Freio", "Marca Bosch", 120.0, 10, 1.5, "Padrão");
        when(service.salvar(any(Produto.class))).thenReturn(p);

        mockMvc.perform(post("/produtos/salvar")
                        .flashAttr("produto", p))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"))
                .andExpect(flash().attributeExists("mensagemSucesso"));
    }

    @Test
    void deveRetornarAoFormularioSeHouverErroDeValidacao() throws Exception {
        Produto p = new Produto(null, "", "Marca Bosch", 120.0, 10, 1.5, "Padrão");
        when(service.salvar(any(Produto.class))).thenThrow(new IllegalArgumentException("O nome da peça é obrigatório e não pode ser vazio."));

        mockMvc.perform(post("/produtos/salvar")
                        .flashAttr("produto", p))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().attributeExists("mensagemErro"))
                .andExpect(model().attribute("mensagemErro", "O nome da peça é obrigatório e não pode ser vazio."));
    }

    @Test
    void deveExibirFormularioDeEdicao() throws Exception {
        Produto p = new Produto(1L, "Pastilha de Freio", "Marca Bosch", 120.0, 10, 1.5, "Padrão");
        when(service.buscarPorId(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/produtos/editar/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("produtos/formulario"))
                .andExpect(model().attributeExists("produto"))
                .andExpect(model().attribute("titulo", "Editar Peça: Pastilha de Freio"));
    }

    @Test
    void deveRedirecionarParaListagemSeProdutoNaoExistirNaEdicao() throws Exception {
        when(service.buscarPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/produtos/editar/99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"))
                .andExpect(flash().attributeExists("mensagemErro"));
    }

    @Test
    void deveExcluirProdutoEGerarMensagemDeSucesso() throws Exception {
        doNothing().when(service).deletar(1L);

        mockMvc.perform(get("/produtos/deletar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/produtos"))
                .andExpect(flash().attributeExists("mensagemSucesso"));
    }
}
