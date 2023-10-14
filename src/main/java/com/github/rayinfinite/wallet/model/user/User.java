package com.github.rayinfinite.wallet.model.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(indexes = {@Index(name = "idx_username", columnList = "username")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, unique = true)
    private String username;

    @Column(length = 64)
    private String password;

    @Column(length = 16)
    private String nickName;

    private String avatar;

    @Column(length = 16)
    private String telephone;

    @Column(length = 64)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime registerTime = LocalDateTime.now();

    private long defaultBook;
}
