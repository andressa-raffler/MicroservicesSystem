package com.raffler.mscreditrating.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardData {
    private Long id;
    private String name;
    private String cardBrand;
    private BigDecimal cardStartLimit;
}
