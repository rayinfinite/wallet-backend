package com.github.rayinfinite.wallet.model.transaction;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(precision = 20, scale = 4)
    private BigDecimal amount;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Category category;
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account account;
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime time=LocalDateTime.now();
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateTime=LocalDateTime.now();
    @Column
    private Boolean deleted = false;
    @Column(nullable = false)
    private Boolean disabled = false;

    private String notes;

    //https://docs.jboss.org/hibernate/orm/6.2/userguide/html_single/Hibernate_User_Guide.html#basic-mapping-json
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> details = new HashMap<>();
}
