package com.github.rayinfinite.wallet.model.book;

import com.github.rayinfinite.wallet.model.account.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
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

    private long income = 0;
    private long expense = 0;
    private long balance = 0;

    @OneToOne(fetch = FetchType.LAZY)
    private Account defaultAccount;
}
