package com.bootcamp.ms_yanki.application.port.in;

import com.bootcamp.ms_yanki.domain.dto.Yankeo;
import com.bootcamp.ms_yanki.domain.model.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiUseCase {

    Mono<Yanki> create(Yanki model);
    Flux<Yanki> findAll();
    Mono<Yankeo> transfer(Yankeo transfer);
    Mono<Void> linkDebitCard(String phone, String debitCardNumber);
}
