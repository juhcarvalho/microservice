package microservice.pedido.producers;

import microservice.pedido.dtos.ProdutoDto;
import microservice.pedido.models.Pedido;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.pedido.name}")
    private String routingKey;

    public void publishMessageEmail(Pedido pedido){
        var produtoDto = new ProdutoDto(
          pedido.getProdutoId(),
          pedido.getQuantidade()
        );

        rabbitTemplate.convertAndSend("", routingKey, produtoDto);
    }
}
