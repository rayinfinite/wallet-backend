package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByBookId(Long bookId, Pageable pageable);

    Page<Transaction> findByBookIdAndTimeBetween(Long book_id, LocalDateTime from, LocalDateTime end,
                                                 Pageable pageable);
}
