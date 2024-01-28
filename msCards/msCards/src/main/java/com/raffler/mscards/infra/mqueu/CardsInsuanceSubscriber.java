package com.raffler.mscards.infra.mqueu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raffler.mscards.domain.Card;
import com.raffler.mscards.domain.ClientCard;
import com.raffler.mscards.domain.InsuanceRequestCardData;
import com.raffler.mscards.infra.repository.CardRepository;
import com.raffler.mscards.infra.repository.ClientCardRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardsInsuanceSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRespository clientCardRespository;

    @RabbitListener(queues = "${mq.queues.cards-issuance}")
    public void recieveInsuanceRequest(@Payload String payload){

        try {
            var mapper = new ObjectMapper();
            InsuanceRequestCardData insuanceRequestCardData = mapper.readValue(payload, InsuanceRequestCardData.class);
            Card card = cardRepository.findById(insuanceRequestCardData.getIdCard()).orElseThrow();
            ClientCard clientCard = new ClientCard();
            clientCard.setCard(card);
            clientCard.setCpf(insuanceRequestCardData.getCpf());
            clientCard.setLimit(insuanceRequestCardData.getReleasedLimit());

            clientCardRespository.save(clientCard);


        } catch (JsonProcessingException e) {
            e.printStackTrace(); //this part is internal, so I can use the printstacktrace
        }
    }
}
