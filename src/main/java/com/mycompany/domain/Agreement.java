package com.mycompany.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "agreements")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "amount_type",length = 6)
    private AmountType amountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "amount_period",length = 10)
    private Period amountPeriod;
    private boolean active;

    public Agreement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    public Period getAmountPeriod() {
        return amountPeriod;
    }

    public void setAmountPeriod(Period amountPeriod) {
        this.amountPeriod = amountPeriod;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agreement agreement = (Agreement) o;
        return active == agreement.active &&
                Objects.equals(id, agreement.id) &&
                Objects.equals(orderNumber, agreement.orderNumber) &&
                Objects.equals(startDate, agreement.startDate) &&
                Objects.equals(endDate, agreement.endDate) &&
                Objects.equals(amount, agreement.amount) &&
                amountType == agreement.amountType &&
                amountPeriod == agreement.amountPeriod;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderNumber, startDate, endDate, amount, amountType, amountPeriod, active);
    }

    @Override
    public String toString() {
        return "Agreement{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", amountType=" + amountType +
                ", amountPeriod=" + amountPeriod +
                ", active=" + active +
                '}';
    }
}
