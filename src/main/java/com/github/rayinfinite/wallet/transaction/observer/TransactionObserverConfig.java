package com.github.rayinfinite.wallet.transaction.observer;

import com.github.rayinfinite.wallet.transaction.TransactionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class TransactionObserverConfig {
    private final TransactionService transactionService;
    private final ObserverBook observerBook;
    private final ObserverAccount observerAccount;

    @PostConstruct
    public void initialize() {
        // 在实例化 TransactionService bean 后，注册观察者
        transactionService.addObserver(observerBook);
        transactionService.addObserver(observerAccount);
    }
}
