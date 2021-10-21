package com.example.rabbitmq.springrabbitmqconsumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rabbitmq.springrabbitmqconsumer.model.Data;

public interface DataRepository extends JpaRepository<Data, Long> {

//    List<Data> findByRoom(String room);
}