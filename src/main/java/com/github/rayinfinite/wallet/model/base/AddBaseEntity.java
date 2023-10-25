package com.github.rayinfinite.wallet.model.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AddBaseEntity {
    private String name;
    private String description;

    private Boolean deleted = false;
    private Boolean disabled = false;

    private BigDecimal income = BigDecimal.ZERO;
    private BigDecimal expense = BigDecimal.ZERO;
    private BigDecimal balance = BigDecimal.ZERO;

    public AddBaseEntity(String name,String description){
        this.name=name;
        this.description=description;
    }
}
