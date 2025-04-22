package com.bootcamp.ms_yanki.application.port.out;

import com.bootcamp.ms_yanki.domain.dto.DebitCardBalance;
import reactor.core.publisher.Mono;


public interface DebitCardServicePort {

    Mono<DebitCardBalance> getDebitCardBalance(String cardNumber);
}
