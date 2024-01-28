package com.raffler.mscreditrating.application;

import com.raffler.mscreditrating.application.ex.BadComunicationWithMSException;
import com.raffler.mscreditrating.application.ex.ClientDataNotFoundException;
import com.raffler.mscreditrating.domain.model.ClientRatingReturn;
import com.raffler.mscreditrating.domain.model.ClientStatus;
import com.raffler.mscreditrating.domain.model.RatingData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;

@RestController
@RequestMapping("credit-rating")
@RequiredArgsConstructor
public class CreditRatingController {

    private final CreditRatingService creditRatingService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "client-status", params = "cpf")
    public ResponseEntity getClientStatus(@PathParam("cpf") String cpf){
        try {
            ClientStatus clientStatus = creditRatingService.getClientStatus(cpf);
            return ResponseEntity.ok(clientStatus);
        } catch (ClientDataNotFoundException e) {
           return ResponseEntity.notFound().build();
        } catch (BadComunicationWithMSException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity rating (@RequestBody RatingData ratingData){
        try {
            ClientRatingReturn clientRatingReturn = creditRatingService.doRate(ratingData.getCpf(),ratingData.getIncome());
            return ResponseEntity.ok(clientRatingReturn);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (BadComunicationWithMSException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

}
