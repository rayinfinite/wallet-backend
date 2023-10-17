package com.github.rayinfinite.wallet.model.account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.rayinfinite.wallet.model.book.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(indexes = {@Index(name = "idx_name", columnList = "name")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();
    private Boolean deleted = false;
    private Boolean disabled = false;

    @Column(precision = 20, scale = 4)
    private BigDecimal income = BigDecimal.ZERO;
    @Column(precision = 20, scale = 4)
    private BigDecimal expense = BigDecimal.ZERO;
    @Column(precision = 20, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    public void expense(BigDecimal amount) {
        this.expense = this.expense.add(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void income(BigDecimal amount) {
        this.income = this.income.add(amount);
        this.balance = this.balance.add(amount);
    }
}
