package com.raffler.mscreditrating.infra.mqueu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raffler.mscreditrating.domain.model.InsuanceRequestCardData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestCardInsuancePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardsInsuanceQueue;

    public void requestCard(InsuanceRequestCardData insuanceRequestCardData) throws JsonProcessingException {
        String json = convertToJSON(insuanceRequestCardData);
        rabbitTemplate.convertAndSend(cardsInsuanceQueue.getName(),json);
    }


    private String convertToJSON(InsuanceRequestCardData insuanceRequestCardData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String insuanceRequestCardDataJson = objectMapper.writeValueAsString(insuanceRequestCardData);
        return insuanceRequestCardDataJson;
    }

}
