package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.model.transaction.dto.AddTransaction;
import com.github.rayinfinite.wallet.model.transaction.dto.UpdateTransaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CurrentSession currentSession;

    public Transaction get(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if(transaction == null) {
            throw new RuntimeException("Transaction not found");
        }
        if(!currentSession.getBook().equals(transaction.getBook())) {
            throw new RuntimeException("No Authority");
        }
        return transaction;
    }

    @Transactional
    public void add(AddTransaction addTransaction) {
        Account account = accountService.get(addTransaction.getAccountId());
        checkAccount(account);
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(addTransaction, transaction);
        transaction.setBook(currentSession.getBook());
        transaction.setAccount(account);
        Map<String, Object> details = new HashMap<>();
        if (addTransaction.getNotes() != null) {
            details.put("notes", addTransaction.getNotes());
        }
        transaction.setDetails(details);
    }

    /**
     * 更新Transaction
     *
     * @param updateTransaction 找本身存在的Transaction，判断在同一个账本上，更新Transaction
     */
    @Transactional
    public void update(UpdateTransaction updateTransaction) {
        Transaction Transaction = transactionRepository.findById(updateTransaction.getId()).orElse(null);
        if (Transaction == null) {
            throw new RuntimeException("Transaction not found");
        }
        Account account = accountService.get(updateTransaction.getAccountId());
        Book book = checkAccount(account);
        if (!book.equals(Transaction.getBook())) {
            throw new RuntimeException("Book has been changed");
        }
        BeanUtils.copyProperties(updateTransaction, Transaction);
        Transaction.setUpdateTime(LocalDateTime.now());
        transactionRepository.save(Transaction);
    }

    @Transactional
    public void delete(Long id) {
        Transaction Transaction = transactionRepository.findById(id).orElse(null);
        if (Transaction == null) {
            throw new RuntimeException("Transaction not found");
        }
        if (!currentSession.getBook().equals(Transaction.getBook())) {
            throw new RuntimeException("No Authority");
        }
        transactionRepository.deleteById(id);
    }

    public Page<Transaction> getPage(int page, int size) {
        Book book = currentSession.getBook();
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findByBookId(book.getId(), pageable);

    }

    public Page<Transaction> getPage(int page, int size, long startTimeMillis, long endTimeMillis) {
        Book book = currentSession.getBook();
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findByBookIdAndTimeBetween(book.getId(), startTimeMillis, endTimeMillis, pageable);
    }


    public Book checkAccount(Account account) {
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        Book book = account.getBook();
        if (!book.equals(currentSession.getBook())) {
            throw new RuntimeException("Book not found");
        }
        return book;
    }
}
