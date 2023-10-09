package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
