package com.bootcamp.ms_yanki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Yankeo {

    private String fromPhone;
    private String toPhone;
    private BigDecimal amount;
    private String description;
}
