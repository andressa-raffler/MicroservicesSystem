package com.raffler.mscards.application;

import com.raffler.mscards.application.DTO.CardSavingRequest;
import com.raffler.mscards.application.DTO.CardsByClientResponse;
import com.raffler.mscards.domain.Card;
import com.raffler.mscards.domain.ClientCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ClientCardService clientService;
    @GetMapping
    public String status(){
    return "ok";
    }

    @PostMapping
    public ResponseEntity register (@RequestBody CardSavingRequest request){
        Card card = request.toModel();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getListOfCardsWithIncomeLessThen (@RequestParam("income") Long income){
        List<Card> list = cardService.getCardsWithIncomeLessOrEqual(income);
        return ResponseEntity.ok(list);

    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient (@RequestParam("cpf") String cpf){
        List<ClientCard> clientCardList = clientService.listCardsByCpf(cpf);
        List<CardsByClientResponse> cardsByClientResponseList = clientCardList.stream()
                .map(CardsByClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cardsByClientResponseList);
    }

}
