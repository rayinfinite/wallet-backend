package com.github.rayinfinite.wallet.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.rayinfinite.wallet.model.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String icon;
    /**
     * 0:expense,1:income,2:transfer
     */
    private int type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Category> child;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    public Category(String name, String icon, int type, Book book) {
        this.name = name;
        this.icon = icon;
        this.type = type;
        this.book = book;
    }
}
