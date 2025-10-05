package microservice.produto.dtos;

public record ProdutoDto(
        Long id,
        String nome,
        Integer quantidade,
        Double preco
) {
}
