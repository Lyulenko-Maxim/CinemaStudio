package com.example.backend.shared;

import com.example.backend.entities.Currency;
import com.example.backend.types.SalaryType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Salary {
    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type")
    private SalaryType type;

    @Expose
    @SerializedName("minAmount")
    @Column(name = "salary_min_amount")
    private BigDecimal minAmount;

    @Expose
    @SerializedName("maxAmount")
    @Column(name = "salary_max_amount")
    private BigDecimal maxAmount;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_currency_id", referencedColumnName = "id")
    private Currency currency;

    public Salary(SalaryType type, BigDecimal minAmount, BigDecimal maxAmount, Currency currency) {
        this.type = type;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.currency = currency;
    }
}
