package com.raffler.mscreditrating.infra.client;

import com.raffler.mscreditrating.domain.model.CardData;
import com.raffler.mscreditrating.domain.model.ClientCards;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "mscards", path = "/cards")
public interface CardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCards>> getCardsByClient (@RequestParam("cpf") String cpf);


    @GetMapping(params = "income")
    ResponseEntity<List<CardData>> getListOfCardsWithIncomeLessThen (@RequestParam("income") Long income);

}
