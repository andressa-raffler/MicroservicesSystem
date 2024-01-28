package com.raffler.mscreditrating.application.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.cards-issuance}")
    private String cardsInsuanceQueue;
    @Bean
    public Queue queueCardsInsuance(){
        return new Queue(cardsInsuanceQueue,true);
    }
}
