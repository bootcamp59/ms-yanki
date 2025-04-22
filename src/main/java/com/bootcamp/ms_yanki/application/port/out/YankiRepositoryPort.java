package com.bootcamp.ms_yanki.application.port.out;

import com.bootcamp.ms_yanki.domain.model.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface YankiRepositoryPort {

    Mono<Yanki> create(Yanki model);
    Flux<Yanki> finAll();
    Mono<Yanki> findByPhone(String phone);
    Flux<Yanki> findByPhones(List<String> phones);
    Mono<Yanki> updateBalance(Yanki model);
}
