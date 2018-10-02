package com.mycompany.dto;

import com.mycompany.domain.AmountType;
import com.mycompany.domain.Period;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AgreementDTO {

    private Long id;
    private Long systemId;
    private String systemName;
    private String orderNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private BigDecimal amount;
    private AmountType amountType;
    private Period amountPeriod;
    private boolean active;
}
