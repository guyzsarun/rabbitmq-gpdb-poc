package com.example.rabbitmq.springrabbitmqconsumer.controller;

import com.example.rabbitmq.springrabbitmqconsumer.model.Data;
import com.example.rabbitmq.springrabbitmqconsumer.model.Message;
import com.example.rabbitmq.springrabbitmqconsumer.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @Autowired
    DataRepository dataRepository;

    @RabbitListener(queues = "queue.A")
    private void receive(Message message) {

        log.info("Message received from QueueA->{}", message);
        Data _data = dataRepository
                .save(new Data(message.getId(), message.getTimestamp(), message.getRoomTemp(),
                        message.getTemp(), message.getHumidity(), message.getPm25()));
    }

    @RabbitListener(queues = "queue.B")
    private void receiveFromB(Message message) {
        log.info("Message received from QueueB->{}", message);
    }
}
