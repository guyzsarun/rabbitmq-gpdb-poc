package com.example.rabbitmq.springrabbitmqproducer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MessageOriginal {
    private long id;
    private String timestamp;
    // temperature in room
    private String roomTemp;
    // outside temperature (real feel)
    private String temp;
    private String humidity;
    private String pm25;
}
