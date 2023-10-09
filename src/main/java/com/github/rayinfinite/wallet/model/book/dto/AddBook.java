package com.github.rayinfinite.wallet.model.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBook {
    private String name;
    private String description;
    private Boolean disabled = false;
    private long balance = 0;
}
