package com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.repository.mongodb;


import com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.entity.YankiEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface YankiRepository extends ReactiveMongoRepository<YankiEntity, String> {

    Mono<YankiEntity> findByPhone(String phone);
    Flux<YankiEntity> findByPhoneIn(List<String> phones);
}
