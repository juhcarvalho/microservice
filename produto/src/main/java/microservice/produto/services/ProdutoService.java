package microservice.produto.services;

import jakarta.persistence.EntityNotFoundException;
import microservice.produto.models.Produto;
import microservice.produto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto create(Produto produto) {
        return repository.save(produto);
    }

    public Produto findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
    }

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto update(Produto produto, Long id) {
        findById(id);
        produto.setId(id);
        return repository.save(produto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void atualizarEstoque(Long produtoId, int quantidade) {
        Produto produto = findById(produtoId);

        if (produto.getQuantidadeEmEstoque() >= quantidade) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);
            update(produto, produtoId);
        } else {
            throw new IllegalArgumentException("Estoque insuficiente para o produto ID: " + produtoId);
        }
    }
}
