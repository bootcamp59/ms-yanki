package com.bootcamp.ms_yanki.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DebitCardBalance {

    private BigDecimal balance;
    private String productId;
}
