package com.github.rayinfinite.wallet.transaction.observer;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.github.rayinfinite.wallet.transaction.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionObserverConfig.class})
@ExtendWith(SpringExtension.class)
class TransactionObserverConfigDiffblueTest {
    @MockBean
    private ObserverAccount observerAccount;

    @MockBean
    private ObserverBook observerBook;

    @Autowired
    private TransactionObserverConfig transactionObserverConfig;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionObserverConfig#initialize()}
     */
    @Test
    void testInitialize() {
        doNothing().when(transactionService).addObserver(Mockito.any());
        transactionObserverConfig.initialize();
        verify(transactionService, atLeast(1)).addObserver(Mockito.any());
    }
}
