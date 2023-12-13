package com.example.backend.shared;

import com.example.backend.entities.Currency;
import com.example.backend.types.SalaryType;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Salary {
    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "salary_type", nullable = false)
    private SalaryType type;

    @Expose
    @Column(name = "salary_min_amount")
    private BigDecimal minAmount;

    @Expose
    @Column(name = "salary_max_amount")
    private BigDecimal maxAmount;

    @Expose
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salary_currency_id", referencedColumnName = "id")
    private Currency currency;
}
