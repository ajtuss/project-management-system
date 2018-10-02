package com.mycompany.dto;

import com.mycompany.domain.Period;
import com.mycompany.domain.System;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AgreementDTO {

    private Long id;
    private System system;
    private String orderNumber;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal amount;
    private Period amountPeriod;
    private Boolean active;
}
