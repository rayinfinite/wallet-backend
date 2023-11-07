package com.github.rayinfinite.wallet.transaction.observer;

import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.Transaction;

import java.math.BigDecimal;

public interface TransactionObserver {
    void transaction(Transaction transaction, Category category, BigDecimal amount);
}
