package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.category.CategoryService;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.AddTransaction;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.transaction.observer.TransactionObserver;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final CurrentSession currentSession;
    private final BookService bookService;
    private final List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Transaction transaction, Category category, BigDecimal amount) {
        for (TransactionObserver observer : observers) {
            observer.transaction(transaction, category,amount);
        }
    }


    public Transaction get(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new RuntimeException("Transaction not found");
        }
        if (!currentSession.getBook().equals(transaction.getBook())) {
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
        transaction.setTime(LocalDateTime.parse(addTransaction.getTime(), DateTimeFormatter.ISO_DATE_TIME));
        Book book = currentSession.getBook();
        transaction.setBook(book);
        transaction.setAccount(account);

        Category category = categoryService.get(addTransaction.getCategoryId());
        transaction.setCategory(category);
//        Map<String, Object> details = new HashMap<>();
//        transaction.setDetails(details);
        transactionRepository.save(transaction);

        notifyObservers(transaction, category,transaction.getAmount());
        currentSession.setBook(bookService.get(transaction.getBook().getId()));
    }

    /**
     * 更新Transaction
     *
     * @param addTransaction 找本身存在的Transaction，判断在同一个账本上，更新Transaction
     */
    @Transactional
    public void update(long id, AddTransaction addTransaction) {
        Transaction transaction = get(id);
        Account account = accountService.get(addTransaction.getAccountId());
        Book book = checkAccount(account);
        BigDecimal originalAmount = new BigDecimal(addTransaction.getAmount().toString());
        BigDecimal transactionAmount = new BigDecimal(transaction.getAmount().toString());
        BigDecimal amount = originalAmount.subtract(transactionAmount);
        if (!book.equals(transaction.getBook())) {
            throw new RuntimeException("Book has been changed");
        }
        BeanUtils.copyProperties(addTransaction, transaction);
        transaction.setUpdateTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        notifyObservers(transaction, transaction.getCategory(),amount);
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new RuntimeException("transaction not found");
        }
        if (!currentSession.getBook().equals(transaction.getBook())) {
            throw new RuntimeException("No Authority");
        }
        notifyObservers(transaction, transaction.getCategory(),transaction.getAmount().negate());
        transactionRepository.deleteById(id);
    }

    public Page<Transaction> getPage(int page, int size) {
        Book book = currentSession.getBook();
        Pageable pageable = PageRequest.of(page - 1, size);
        return transactionRepository.findByBookId(book.getId(), pageable);

    }

    public Page<Transaction> getPage(int page, int size, LocalDateTime from, LocalDateTime end) {
        Book book = currentSession.getBook();
        Pageable pageable = PageRequest.of(page - 1, size);
        return transactionRepository.findByBookIdAndTimeBetween(book.getId(), from, end, pageable);
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
