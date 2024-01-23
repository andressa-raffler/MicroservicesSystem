package com.raffler.mscards.application.DTO;

import com.raffler.mscards.domain.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByClientResponse {
    private String name;
    private String cardBrand;
    private BigDecimal limit;


    public static CardsByClientResponse fromModel(ClientCard model){
        return new CardsByClientResponse(
                model.getCard().getName(),
                model.getCard().getCardBrand().toString(),
                model.getLimit()
        ) ;
    }
}
