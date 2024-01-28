package com.raffler.mscreditrating.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InsuanceRequestCardData {
    private Long idCard;
    private String cpf;
    private String address;
    private BigDecimal releasedLimit;
}
