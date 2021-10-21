package com.example.rabbitmq.springrabbitmqconsumer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rabbitmq.springrabbitmqconsumer.model.Data;
import com.example.rabbitmq.springrabbitmqconsumer.repository.DataRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DataController {
    @Autowired
    DataRepository dataRepository;

    @GetMapping("/data")
    public ResponseEntity<List<Data>> getAllData(@RequestParam(required = false) String room) {
        try {
            List<Data> data = new ArrayList<Data>();

            if (room == null)
                data.addAll(dataRepository.findAll());
//            else
//                data.addAll(dataRepository.findByRoom(room));

            if (data.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable("id") long id) {
        Optional<Data> dataData = dataRepository.findById(id);

        return dataData.map(data -> new ResponseEntity<>(data, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/data")
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        try {
            Data _data = dataRepository
                    .save(new Data(data.getId(), data.getTimestamp(), data.getRoomTemp(),
                            data.getTemp(), data.getHumidity(), data.getPm25()));
            return new ResponseEntity<>(_data, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/data/{id}")
    public ResponseEntity<Data> updateData(@PathVariable("id") long id, @RequestBody Data data) {
        Optional<Data> dataData = dataRepository.findById(id);

        if (dataData.isPresent()) {
            Data _data = dataData.get();
            _data.setTimestamp(data.getTimestamp());
            _data.setRoomTemp(data.getRoomTemp());
            _data.setTemp(data.getTemp());
            _data.setHumidity(data.getHumidity());
            _data.setPm25(data.getPm25());
            return new ResponseEntity<>(dataRepository.save(_data), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<HttpStatus> deleteData(@PathVariable("id") long id) {
        try {
            dataRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/data")
    public ResponseEntity<HttpStatus> deleteAllData() {
        try {
            dataRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
