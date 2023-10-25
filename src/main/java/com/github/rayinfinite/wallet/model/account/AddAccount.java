package com.github.rayinfinite.wallet.model.account;

import com.github.rayinfinite.wallet.model.base.AddBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddAccount extends AddBaseEntity {
    public AddAccount(String name,String description){
        super(name,description);
    }
}
