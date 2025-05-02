package com.bootcamp.ms_yanki.infrastructure.adapter.out.kafka;

import com.bootcamp.ms_yanki.application.port.out.YankiEventPublisher;
import com.bootcamp.ms_yanki.domain.model.Yanki;
import com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.repository.mongodb.YankiRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class YankiEventPublisherKafka implements YankiEventPublisher {

    private final KafkaTemplate<String, Yanki> kafkaTemplate;
    private final YankiRepository yankiRepository;

    @Override
    public void publishLinkDebitCard(Yanki yanki) {
        log.info(String.format("Enviando mensaje a alibou topic:: %s", yanki.getDebitCardNumber()));
        kafkaTemplate.send("alibou", yanki.getId(), yanki);
    }

    /*@KafkaListener(topics = "yanki.debitcard.link.response", groupId = "ms-yanki")
    public void consumeLinkResponse(Yanki event) {
        yankiRepository.findById(event.getId())
            .flatMap(yanki -> {
                yanki.setAssociationStatus(event.getAssociationStatus());
                return yankiRepository.save(yanki);
            })
            .subscribe();
    }

     */
}
