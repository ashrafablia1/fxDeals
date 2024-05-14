package com.progresssoft.fxDeals.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "deal_id")
    @NotNull
    private UUID dealId;

    @Column(name = "from_currency")
    @NotBlank(message = "From Currency not valid")
    private String fromCurrency;

    @Column(name = "to_currency")
    @NotBlank(message = "To Currency not valid")
    private String toCurrency;

    @Column(name = "deal_time")
    @NotNull(message = "To Currency not valid")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant dealTime;

    @Column(name = "amount")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount not valid")
    private double amount;


    public Deal(UUID dealId, String fromCurrency, String toCurrency, Instant dealTime, double amount) {
        this.dealId = dealId;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.dealTime = dealTime;
        this.amount = amount;
    }

    public Deal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getDealId() {
        return dealId;
    }

    public void setDealId(UUID dealId) {
        this.dealId = dealId;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Instant getDealTime() {
        return dealTime;
    }

    public void setDealTime(Instant dealTime) {
        this.dealTime = dealTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
