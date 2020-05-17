//package com.example.spring.producer.api;
package VotacaoAssembleia.producer.api;

//import com.example.spring.producer.dto.MessageQueue;
import VotacaoAssembleia.producer.dto.MessageQueue;
//import com.example.spring.producer.service.AmqpService;
import VotacaoAssembleia.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AmqpApi {

    @Autowired
    private AmqpService service;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/send")
    public void sendToConsumer(@RequestBody MessageQueue message) {
        service.sendToConsumer(message);
    }


    public void sendTeste(@RequestBody MessageQueue message) {
        System.out.println("message: "+message);
        service.sendToConsumer(message);
    }




}