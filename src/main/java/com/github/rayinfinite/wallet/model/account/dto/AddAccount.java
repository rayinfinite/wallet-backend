package com.github.rayinfinite.wallet.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddAccount {
    private String name;
    private String description;

    private Boolean deleted = false;
    private Boolean disabled = false;

    private long income = 0;
    private long expense = 0;
    private long balance = 0;

    public AddAccount(String name,String description){
        this.name=name;
        this.description=description;
    }
}
