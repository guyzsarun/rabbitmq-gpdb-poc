package com.example.rabbitmq.springrabbitmqproducer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Message {
    private List<MessageOriginal> bList;
}
