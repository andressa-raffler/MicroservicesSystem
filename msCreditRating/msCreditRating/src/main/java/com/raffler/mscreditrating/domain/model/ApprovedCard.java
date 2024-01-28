package com.raffler.mscreditrating.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApprovedCard {
    private String card;
    private String cardBrand;
    private BigDecimal approvedLimit;


}
