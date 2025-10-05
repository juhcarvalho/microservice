package microservice.produto.controllers;

import microservice.produto.models.Produto;
import microservice.produto.services.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> getProduto() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return service.create(produto);
    }

    @PostMapping("/{id}")
    public Produto updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return service.update(produto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        service.delete(id);
    }

    public void abaterEstoque(Long produtoId, int quantidade) {
        service.atualizarEstoque(produtoId, -quantidade);
    }
}
