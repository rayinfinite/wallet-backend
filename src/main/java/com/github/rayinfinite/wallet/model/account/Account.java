package com.github.rayinfinite.wallet.model.account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.rayinfinite.wallet.model.base.BaseEntity;
import com.github.rayinfinite.wallet.model.book.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(indexes = {@Index(name = "idx_name", columnList = "name")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account extends BaseEntity {
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;
}
