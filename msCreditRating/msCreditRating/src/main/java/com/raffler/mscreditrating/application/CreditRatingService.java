package com.raffler.mscreditrating.application;

import com.raffler.mscreditrating.application.ex.BadComunicationWithMSException;
import com.raffler.mscreditrating.application.ex.ClientDataNotFoundException;
import com.raffler.mscreditrating.domain.model.*;
import com.raffler.mscreditrating.infra.client.CardResourceClient;
import com.raffler.mscreditrating.infra.client.ClientResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditRatingService {


    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;
    public ClientStatus getClientStatus(String cpf) throws ClientDataNotFoundException, BadComunicationWithMSException {
        try {
            ResponseEntity<List<ClientCards>> clientCardsResponseEntity = cardResourceClient.getCardsByClient(cpf);
            ResponseEntity<ClientData> clientDataResponseEntity = clientResourceClient.clientData(cpf);
            return ClientStatus
                    .builder()
                    .clientData(clientDataResponseEntity.getBody())
                    .clientCardsList(clientCardsResponseEntity.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFoundException();
            }
            throw new BadComunicationWithMSException(e.getMessage(),e.status() );
        }

    }

    public ClientRatingReturn doRate(String cpf, Long income) throws BadComunicationWithMSException, ClientDataNotFoundException {
        try {
            ResponseEntity<ClientData> clientDataResponseEntity = clientResourceClient.clientData(cpf);
            ResponseEntity<List<CardData>> listOfCardsWithIncomeResponseEntity = cardResourceClient.getListOfCardsWithIncomeLessThen(income);

            List<CardData> cardDataList = listOfCardsWithIncomeResponseEntity.getBody();
            List<ApprovedCard> approvedCards = cardDataList.stream().map(cardData -> {

                ClientData clientData = clientDataResponseEntity.getBody();


                BigDecimal cardStartLimit = cardData.getCardStartLimit();
                BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal bdClientAge = BigDecimal.valueOf(clientData.getAge());

                BigDecimal factor = bdClientAge.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(cardStartLimit);


                ApprovedCard approved = new ApprovedCard();
                approved.setCardBrand(cardData.getCardBrand());
                approved.setCard(cardData.getName());
                approved.setApprovedLimit(approvedLimit);

                return approved;

            }).collect(Collectors.toList());

            ClientRatingReturn clientRatingReturn = new ClientRatingReturn(approvedCards);
            return clientRatingReturn;

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new BadComunicationWithMSException(e.getMessage(), e.status());
        }
    }


}
