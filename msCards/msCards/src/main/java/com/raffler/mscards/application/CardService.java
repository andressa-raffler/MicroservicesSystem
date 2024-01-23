package com.raffler.mscards.application;

import com.raffler.mscards.domain.Card;
import com.raffler.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    @Transactional
    public Card save (Card card){
        return cardRepository.save(card);
    }

    public List<Card> getCardsWithIncomeLessOrEqual(Long income){
        BigDecimal bidDecimalIncome = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(bidDecimalIncome);
    }
}
