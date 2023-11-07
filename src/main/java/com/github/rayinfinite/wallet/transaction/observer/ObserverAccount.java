package com.github.rayinfinite.wallet.transaction.observer;

import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ObserverAccount implements TransactionObserver{
    private final AccountService accountService;

    @Override
    public void transaction(Transaction transaction, Category category, BigDecimal amount) {
        Account account = accountService.get(transaction.getAccount().getId());
        if (category.getType() == 0) {
            account.expense(amount);
        } else {
            account.income(amount);
        }
        accountService.save(account);
    }
}
