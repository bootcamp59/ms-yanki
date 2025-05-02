package com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.mapper;

import com.bootcamp.ms_yanki.domain.model.Yanki;
import com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.entity.YankiEntity;

public class YankiEntityMapper {

    public static Yanki toModel(YankiEntity entity){
        return Yanki.builder()
            .id(entity.getId())
            .documentNumber(entity.getDocumentNumber())
            .imei(entity.getImei())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .debitCardNumber(entity.getDebitCardNumber())
            .balance(entity.getBalance())
            .associationStatus(entity.getAssociationStatus())
            .build();
    }

    public static YankiEntity toEntity(Yanki model){
        return YankiEntity.builder()
            .id(model.getId())
            .documentNumber(model.getDocumentNumber())
            .imei(model.getImei())
            .email(model.getEmail())
            .phone(model.getPhone())
            .debitCardNumber(model.getDebitCardNumber())
            .balance(model.getBalance())
            .associationStatus(model.getAssociationStatus())
            .build();
    }
}
