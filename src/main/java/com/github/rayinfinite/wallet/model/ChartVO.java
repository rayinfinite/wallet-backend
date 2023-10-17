package com.github.rayinfinite.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ChartVO {
    private String x;
    private BigDecimal y;
}
