package com.mycompany.dto;

import com.mycompany.domain.AmountType;
import com.mycompany.domain.Period;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AgreementDTO {

    private Long id;

    @NotNull
    private Long systemId;

    private String systemName;

    @Size(min = 3, max = 255)
    private String orderNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @DecimalMin("0.0")
    private BigDecimal amount;

    @NotNull
    private AmountType amountType;

    @NotNull
    private Period amountPeriod;

    private boolean active;

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber.trim();
    }
}
