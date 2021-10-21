package com.example.rabbitmq.springrabbitmqconsumer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class Message {
    private int id;
    private String timestamp;
    // in-room temperature
    private String roomTemp;
    // outside temperature (real feel)
    private String temp;
    private String humidity;
    private String pm25;
}
