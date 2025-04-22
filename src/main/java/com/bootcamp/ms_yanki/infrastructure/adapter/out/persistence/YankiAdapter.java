package com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence;

import com.bootcamp.ms_yanki.application.port.out.YankiRepositoryPort;
import com.bootcamp.ms_yanki.domain.model.Yanki;
import com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.mapper.YankiEntityMapper;
import com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.repository.mongodb.YankiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class YankiAdapter implements YankiRepositoryPort {

    private final YankiRepository repository;

    @Override
    public Mono<Yanki> create(Yanki model) {
        return repository.save(YankiEntityMapper.toEntity(model))
            .map(YankiEntityMapper::toModel);
    }

    @Override
    public Flux<Yanki> finAll() {
        return repository.findAll()
            .map(YankiEntityMapper::toModel);
    }

    @Override
    public Mono<Yanki> findByPhone(String phone) {
        return repository.findByPhone(phone)
            .map(YankiEntityMapper::toModel);
    }

    @Override
    public Flux<Yanki> findByPhones(List<String> phones) {
        return repository.findByPhoneIn(phones)
                .map(YankiEntityMapper::toModel);
    }

    @Override
    public Mono<Yanki> updateBalance(Yanki model) {
        return repository.save(YankiEntityMapper.toEntity(model))
            .map(YankiEntityMapper::toModel);
    }
}
