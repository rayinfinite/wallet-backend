package com.github.rayinfinite.wallet.model.transaction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddTransaction {
    @NotNull
    private Long categoryId;
    @NotNull
    private Long amount;

    private Long accountId;

    private Boolean disabled;

    private String notes;
}
