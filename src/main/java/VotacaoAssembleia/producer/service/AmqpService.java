package VotacaoAssembleia.producer.service;

import VotacaoAssembleia.producer.dto.MessageQueue;

public interface AmqpService {
    void sendToConsumer(MessageQueue message);
}
