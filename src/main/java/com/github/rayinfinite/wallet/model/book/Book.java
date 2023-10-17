package com.github.rayinfinite.wallet.model.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long userId;

    private LocalDateTime createTime=LocalDateTime.now();
    private LocalDateTime updateTime=LocalDateTime.now();
    private Boolean deleted = false;
    private Boolean disabled = false;

    @Column(precision = 20, scale = 4)
    private BigDecimal income = BigDecimal.ZERO;
    @Column(precision = 20, scale = 4)
    private BigDecimal expense = BigDecimal.ZERO;
    @Column(precision = 20, scale = 4)
    private BigDecimal balance = BigDecimal.ZERO;

    private long defaultAccount;

    public void expense(BigDecimal amount) {
        this.expense = this.expense.add(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void income(BigDecimal amount) {
        this.income = this.income.add(amount);
        this.balance = this.balance.add(amount);
    }
}
