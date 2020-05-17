package VotacaoAssembleia.producer.service.implementation;

import VotacaoAssembleia.producer.amqp.AmqpProducer;
import VotacaoAssembleia.producer.dto.MessageQueue;
import VotacaoAssembleia.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements AmqpService {

    @Autowired
    private AmqpProducer<MessageQueue> amqp;

    @Override
    public void sendToConsumer(MessageQueue message) {
        amqp.producer(message);
    }
}
