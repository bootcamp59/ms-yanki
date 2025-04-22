package com.bootcamp.ms_yanki.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Yanki {

    private String id;
    private String documentNumber;
    private String phone;
    private String imei;
    private String email;
    private BigDecimal balance;
    private String debitCardNumber;
}
