package com.github.rayinfinite.wallet.model.book;

import com.github.rayinfinite.wallet.model.base.AddBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AddBook extends AddBaseEntity {
    public AddBook(String name,String description){
        super(name,description);
    }
}
