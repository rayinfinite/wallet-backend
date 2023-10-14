package com.github.rayinfinite.wallet.model.account;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.github.rayinfinite.wallet.model.book.Book;
import jakarta.persistence.*;
import lombok.Data;

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

    private long income = 0;
    private long expense = 0;
    private long balance = 0;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;
}
