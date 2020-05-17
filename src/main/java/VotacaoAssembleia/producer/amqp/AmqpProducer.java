//package com.example.spring.producer.amqp;
package VotacaoAssembleia.producer.amqp;


public interface AmqpProducer<T> {
    void producer(T t);
}
