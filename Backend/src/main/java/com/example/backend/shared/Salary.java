package com.example.backend.shared;

import com.example.backend.types.CurrencyType;
import com.example.backend.types.SalaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class Salary {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SalaryType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private CurrencyType currency;
}
