package br.edu.ifgoiano.estoque_autopecas.controller;

import br.edu.ifgoiano.estoque_autopecas.model.Produto;
import br.edu.ifgoiano.estoque_autopecas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model, @RequestParam(value = "search", required = false) String search) {
        List<Produto> produtos;
        if (search != null && !search.trim().isEmpty()) {
            produtos = service.buscarPorNome(search);
            model.addAttribute("search", search.trim());
        } else {
            produtos = service.listarTodos();
        }
        model.addAttribute("produtos", produtos);

        // Estatísticas do painel
        int totalItens = produtos.stream().mapToInt(p -> p.getQuantidade() != null ? p.getQuantidade() : 0).sum();
        double valorTotal = produtos.stream()
                .mapToDouble(p -> (p.getPreco() != null && p.getQuantidade() != null) ? p.getPreco() * p.getQuantidade() : 0.0)
                .sum();
        long totalPecasUnicas = produtos.size();
        long alertaEstoqueBaixo = produtos.stream()
                .filter(p -> p.getQuantidade() != null && p.getQuantidade() < 5)
                .count();

        model.addAttribute("totalItens", totalItens);
        model.addAttribute("valorTotal", valorTotal);
        model.addAttribute("totalPecasUnicas", totalPecasUnicas);
        model.addAttribute("alertaEstoqueBaixo", alertaEstoqueBaixo);

        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("titulo", "Cadastrar Nova Peça");
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("produto") Produto produto, RedirectAttributes redirectAttributes, Model model) {
        try {
            service.salvar(produto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Peça salva com sucesso no estoque!");
            return "redirect:/produtos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensagemErro", e.getMessage());
            model.addAttribute("titulo", produto.getId() == null ? "Cadastrar Nova Peça" : "Editar Peça");
            return "produtos/formulario";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        return service.buscarPorId(id)
                .map(produto -> {
                    model.addAttribute("produto", produto);
                    model.addAttribute("titulo", "Editar Peça: " + produto.getNome());
                    return "produtos/formulario";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("mensagemErro", "Peça não encontrada no estoque.");
                    return "redirect:/produtos";
                });
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            service.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Peça excluída com sucesso do estoque!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao excluir a peça.");
        }
        return "redirect:/produtos";
    }
}
