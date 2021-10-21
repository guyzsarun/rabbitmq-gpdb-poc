package com.example.rabbitmq.springrabbitmqproducer.controller;

import com.example.rabbitmq.springrabbitmqproducer.model.Message;
import com.example.rabbitmq.springrabbitmqproducer.model.MessageOriginal;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Autowired
//    private DirectExchange exchange;
    @Autowired
    private FanoutExchange exchange;

    @PostMapping("/post")
    public String send(@RequestBody List<MessageOriginal> message){
        for (MessageOriginal messageOriginal : message) {
            rabbitTemplate.convertAndSend(exchange.getName(), "", messageOriginal);
        }
        return "Message is sent successfully!";
    }

}
