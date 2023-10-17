package com.github.rayinfinite.wallet.model.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddTransaction {
    @NotNull
    private Long categoryId;
    @NotNull
    private BigDecimal amount;

    private Long accountId;

    private String notes;

    private String type;
    private String time;
}
