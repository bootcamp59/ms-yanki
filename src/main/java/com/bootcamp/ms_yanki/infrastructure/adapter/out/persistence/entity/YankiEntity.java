package com.bootcamp.ms_yanki.infrastructure.adapter.out.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value="yanki")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YankiEntity {

    @Id
    private String id;
    private String documentNumber;
    private String phone;
    private String imei;
    private String email;
    private BigDecimal balance;
    private String debitCardNumber;
    private String associationStatus; // PENDING, CONFIRMED, REJECTED
}
