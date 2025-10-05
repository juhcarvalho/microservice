package microservice.pedido.services;

import microservice.pedido.models.Pedido;
import microservice.pedido.producers.PedidoProducer;
import microservice.pedido.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final PedidoProducer pedidoProducer;

    public PedidoService(PedidoRepository pedidoRepository, PedidoProducer pedidoProducer) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProducer = pedidoProducer;
    }

    /**
     * Realiza o pedido.
     * @param pedido o pedido a ser criado
     * @return o pedido salvo
     */
    public Pedido create(Pedido pedido){
        pedido =  pedidoRepository.save(pedido);
        pedidoProducer.publishMessageEmail(pedido);
        return pedido;
    }

}
