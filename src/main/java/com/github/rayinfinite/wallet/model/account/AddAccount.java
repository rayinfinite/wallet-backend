package com.github.rayinfinite.wallet.model.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAccount {
    private String name;
    private String description;

    private Boolean deleted = false;
    private Boolean disabled = false;

    private BigDecimal income = BigDecimal.ZERO;
    private BigDecimal expense = BigDecimal.ZERO;
    private BigDecimal balance = BigDecimal.ZERO;

    public AddAccount(String name,String description){
        this.name=name;
        this.description=description;
    }
}
