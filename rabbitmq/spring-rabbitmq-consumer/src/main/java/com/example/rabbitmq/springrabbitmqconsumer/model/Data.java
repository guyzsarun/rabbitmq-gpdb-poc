package com.example.rabbitmq.springrabbitmqconsumer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "data")
@Getter
@Setter
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    @Column(name = "ROOM_TEMP")
    private String roomTemp;

    @Column(name = "TEMP")
    private String temp;

    @Column(name = "HUMIDITY")
    private String humidity;

    @Column(name = "PM25")
    private String pm25;

    public Data() {

    }

    public Data(long id, String timestamp, String roomTemp, String temp, String humidity, String pm25) {
        this.id = id;
        this.timestamp = timestamp;
        this.roomTemp = roomTemp;
        this.temp = temp;
        this.humidity = humidity;
        this.pm25 = pm25;
    }

    @Override
    public String toString() {
        return "Data [id=" + id + ", timestamp=" + timestamp + ", temp1=" + roomTemp + ", temp2=" + temp +
                ", humidity=" + humidity + ", pm2.5=" + pm25 + "]";
    }
}