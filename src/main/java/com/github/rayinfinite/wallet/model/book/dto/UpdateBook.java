package com.github.rayinfinite.wallet.model.book.dto;

import lombok.Data;

@Data
public class UpdateBook {
    private Long id;
    private String name;
    private String description;
    private Boolean disabled;
    private long income;
    private long expense;
    private long balance;
}
