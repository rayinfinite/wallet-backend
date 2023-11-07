package com.github.rayinfinite.wallet.transaction.observer;

import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ObserverBook implements TransactionObserver{
    private final BookService bookService;

    @Override
    public void transaction(Transaction transaction, Category category, BigDecimal amount) {
        Book book = bookService.get(transaction.getBook().getId());
        if (category.getType() == 0) {
            book.expense(amount);
        } else {
            book.income(amount);
        }
        bookService.save(book);
    }
}
