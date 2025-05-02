package com.bootcamp.ms_yanki.infrastructure.adapter.in.mapper;

import com.bootcamp.ms_yanki.domain.model.Yanki;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.model.YankiDto;
import com.bootcamp.ms_yanki.infrastructure.adapter.in.model.YankiLinkRequest;

public class OpenApiMapper {

    public static YankiDto toDto(Yanki model){
        var dto = new YankiDto();
        dto.documentNumber(model.getDocumentNumber());
        dto.debitCardNumber(model.getDebitCardNumber());
        dto.balance(model.getBalance());
        dto.email(model.getEmail());
        dto.imei(model.getImei());
        dto.phone(model.getPhone());
        return dto;
    }

    public static Yanki toModel(YankiDto dto){
        return Yanki.builder()
            .documentNumber(dto.getDocumentNumber())
            .debitCardNumber(dto.getDebitCardNumber())
            .balance(dto.getBalance())
            .email(dto.getEmail())
            .imei(dto.getImei())
            .phone(dto.getPhone())
            .build();
    }


}
