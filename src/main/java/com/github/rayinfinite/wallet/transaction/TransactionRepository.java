package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByBookId(Long bookId, Pageable pageable);

    Page<Transaction> findByBookIdAndTimeBetween(Long bookId, long startTimeMillis, long endTimeMillis,
                                                 Pageable pageable);
}
