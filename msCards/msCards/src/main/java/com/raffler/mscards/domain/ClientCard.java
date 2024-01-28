package com.raffler.mscards.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class ClientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Column(name = "`limit`")
    private BigDecimal limit;

}
