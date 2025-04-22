package com.bootcamp.ms_yanki.infrastructure.adapter.out.client;

import com.bootcamp.ms_yanki.application.port.out.DebitCardServicePort;
import com.bootcamp.ms_yanki.domain.dto.DebitCardBalance;
import com.bootcamp.ms_yanki.infrastructure.config.YankiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class DebitCardServiceAdapter implements DebitCardServicePort {

    private final WebClient.Builder webClientBuilder;
    private final YankiProperties properties;

    @Override
    public Mono<DebitCardBalance> getDebitCardBalance(String cardNumber) {
        var url = properties.getMsDebitCardPath() + "/balance-principal/" + cardNumber;

        return webClientBuilder.build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(DebitCardBalance.class)
                .doOnNext(f -> {
                    log.info("conexion exitosa al serivicio: {}", url + f);
                })
                .doOnError( err ->  log.info("no se logro la conexion al serivicio: {}", url));
    }
}
