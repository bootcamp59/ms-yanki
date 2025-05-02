package com.bootcamp.ms_yanki.application.port.out;

import com.bootcamp.ms_yanki.domain.model.Yanki;

public interface YankiEventPublisher {

    void publishLinkDebitCard(Yanki yanki);
}
