package com.bootcamp.ms_yanki.infrastructure.adapter.in.expose;

import com.bootcamp.ms_yanki.application.port.in.YankiUseCase;
import com.bootcamp.ms_yanki.domain.dto.Yankeo;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.api.YankiApi;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.mapper.OpenApiMapper;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.model.TransactionDto;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.model.YankiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class YankiController implements YankiApi {

    private final YankiUseCase useCase;

    @Override
    public Mono<ResponseEntity<YankiDto>> create(Mono<YankiDto> yankiDto, ServerWebExchange exchange) {
        return yankiDto
            .flatMap(dto -> useCase.create(OpenApiMapper.toModel(dto)))
            .map( model -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(OpenApiMapper.toDto(model)));
    }

    @Override
    public Mono<ResponseEntity<Flux<YankiDto>>> findAll(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(useCase.findAll().map(OpenApiMapper::toDto)));
    }

    @Override
    public Mono<ResponseEntity<TransactionDto>> transfer(Mono<TransactionDto> transactionDto, ServerWebExchange exchange) {
        return null;
    }

    @PostMapping("/yanki/yankear")
    public Mono<Yankeo> yankeo(@RequestBody Yankeo request){
        return useCase.transfer(request);
    }
}
