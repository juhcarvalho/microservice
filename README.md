## Sistema de Compras Online

### 1. Microsserviço de Produtos

Responsável por cadastrar produtos (id, nome, preço, quantidadeEmEstoque).

Deve controlar atualização de estoque (ex.: reduzir quantidade quando houver venda).

### 2. Microsserviço de Pedidos

Responsável por criar e gerenciar pedidos (idPedido, listaProdutos, valorTotal, status).

Quando um pedido é criado, precisa comunicar o microsserviço de Produtos para atualizar a quantidade do estoque.

### Fluxo esperado

🔷 O cliente faz um pedido.

🔷 O serviço de Pedidos envia uma solicitação para o serviço de Produtos.

🔷 o serviço de Produtos confirma a reserva e diminui o estoque.