//package com.example.spring.producer.service;
package VotacaoAssembleia.producer.service;

//import com.example.spring.producer.dto.MessageQueue;
import VotacaoAssembleia.producer.dto.MessageQueue;

public interface AmqpService {
    void sendToConsumer(MessageQueue message);
}
