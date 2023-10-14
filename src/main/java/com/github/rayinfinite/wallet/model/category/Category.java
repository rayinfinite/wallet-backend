package com.github.rayinfinite.wallet.model.category;

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

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String icon;

    private int type;

    @OneToMany
    private List<Category> child;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Book book;

    public Category(String name,String icon,int type){
        this.name=name;
        this.icon=icon;
        this.type=type;
    }
}
