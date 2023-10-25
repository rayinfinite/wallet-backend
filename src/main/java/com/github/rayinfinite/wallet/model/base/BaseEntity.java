package com.github.rayinfinite.wallet.model.base;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

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

    public void expense(BigDecimal amount) {
        this.expense = this.expense.add(amount);
        this.balance = this.balance.subtract(amount);
    }

    public void income(BigDecimal amount) {
        this.income = this.income.add(amount);
        this.balance = this.balance.add(amount);
    }
}
