package com.raffler.mscards.application.DTO;

import com.raffler.mscards.domain.Card;
import com.raffler.mscards.domain.CardBrand;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardSavingRequest {

    private String name;
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal cardStartLimit;

    public Card toModel (){
        return new Card(name, cardBrand,income, cardStartLimit);
    }

}
