package com.github.rayinfinite.wallet.model.transaction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTransaction {
    private Long id;
    private Long categoryId;
    private Long amount;
    private Long accountId;
    private Boolean disabled;
    private String notes;
}
