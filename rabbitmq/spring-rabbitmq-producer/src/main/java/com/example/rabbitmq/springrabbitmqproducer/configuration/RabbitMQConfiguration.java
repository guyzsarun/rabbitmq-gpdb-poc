package com.example.rabbitmq.springrabbitmqproducer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";

    @Bean
    Queue queueA() {
        return new Queue("queue.A", false);
    }

    @Bean
    Queue queueB() {
        return new Queue("queue.B", false);
    }

    @Bean
    Queue queueAll() {
        return new Queue("queue.all", false);
    }



//    @Bean
//    DirectExchange exchange(){
//        return new DirectExchange("exchange.direct");
//    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
//    Binding bindingA(Queue queueA, DirectExchange exchange){
    Binding bindingA(Queue queueA, FanoutExchange exchange){
        return BindingBuilder.bind(queueA)
                .to(exchange);
        // because fanout is no need the routing key
//                .with(ROUTING_A);
    }

    @Bean
//    Binding bindingB(Queue queueB, DirectExchange exchange){
    Binding bindingB(Queue queueB, FanoutExchange exchange){
        return BindingBuilder.bind(queueB)
                .to(exchange);
//                .with(ROUTING_B);
    }

    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
