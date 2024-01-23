package com.raffler.mscards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;
    private BigDecimal income;
    private BigDecimal cardStartLimit;

    public Card(String name, CardBrand cardBrand, BigDecimal income, BigDecimal cardStartLimit) {
        this.name = name;
        this.cardBrand = cardBrand;
        this.income = income;
        this.cardStartLimit = cardStartLimit;
    }
}
