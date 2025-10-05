package microservice.produto.consumers;

import microservice.produto.dtos.ProdutoDto;
import microservice.produto.services.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConsumer {
    private final ProdutoService service;

    public ProdutoConsumer(ProdutoService service) {
        this.service = service;
    }

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void listenPedidoQueue(@Payload ProdutoDto dto) {
        try {
            service.atualizarEstoque(dto.id(), dto.quantidade());
            System.out.println("Produto atualizado: " + dto.id());
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}