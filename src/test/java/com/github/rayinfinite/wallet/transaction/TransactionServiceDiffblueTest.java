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

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionService.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceDiffblueTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private BookService bookService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CurrentSession currentSession;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionService#addObserver(TransactionObserver)}
     */
    @Test
    void testAddObserver() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TransactionService.accountService
        //     TransactionService.bookService
        //     TransactionService.categoryService
        //     TransactionService.currentSession
        //     TransactionService.observers
        //     TransactionService.transactionRepository

        transactionService.addObserver(mock(TransactionObserver.class));
    }

    /**
     * Method under test: {@link TransactionService#get(Long)}
     */
    @Test
    void testGet() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book4);
        Transaction actualGetResult = transactionService.get(1L);
        verify(currentSession).getBook();
        verify(transactionRepository).findById(Mockito.<Long>any());
        assertSame(transaction, actualGetResult);
    }

    /**
     * Method under test: {@link TransactionService#get(Long)}
     */
    @Test
    void testGet2() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(currentSession.getBook()).thenThrow(new RuntimeException("foo"));
        assertThrows(RuntimeException.class, () -> transactionService.get(1L));
        verify(currentSession).getBook();
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#get(Long)}
     */
    @Test
    void testGet3() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getBook()).thenReturn(book4);
        doNothing().when(transaction).setAccount(Mockito.<Account>any());
        doNothing().when(transaction).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(transaction).setBook(Mockito.<Book>any());
        doNothing().when(transaction).setCategory(Mockito.<Category>any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.<Map<String, Object>>any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.<String>any());
        doNothing().when(transaction).setTime(Mockito.<LocalDateTime>any());
        doNothing().when(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book5);
        transactionService.get(1L);
        verify(currentSession).getBook();
        verify(transaction).getBook();
        verify(transaction).setAccount(Mockito.<Account>any());
        verify(transaction).setAmount(Mockito.<BigDecimal>any());
        verify(transaction).setBook(Mockito.<Book>any());
        verify(transaction).setCategory(Mockito.<Category>any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.<Map<String, Object>>any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.<String>any());
        verify(transaction).setTime(Mockito.<LocalDateTime>any());
        verify(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#get(Long)}
     */
    @Test
    void testGet4() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Book book4 = mock(Book.class);

        doNothing().when(book4).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book4).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book4).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book4).setDescription(Mockito.<String>any());
        doNothing().when(book4).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book4).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book4).setId(Mockito.<Long>any());
        doNothing().when(book4).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book4).setName(Mockito.<String>any());
        doNothing().when(book4).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book4).setDefaultAccount(anyLong());
        doNothing().when(book4).setUserId(Mockito.<Long>any());
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getBook()).thenReturn(book4);
        doNothing().when(transaction).setAccount(Mockito.<Account>any());
        doNothing().when(transaction).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(transaction).setBook(Mockito.<Book>any());
        doNothing().when(transaction).setCategory(Mockito.<Category>any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.<Map<String, Object>>any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.<String>any());
        doNothing().when(transaction).setTime(Mockito.<LocalDateTime>any());
        doNothing().when(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book5);
        assertThrows(RuntimeException.class, () -> transactionService.get(1L));
        verify(currentSession).getBook();
        verify(book4).setBalance(Mockito.<BigDecimal>any());
        verify(book4).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book4).setDeleted(Mockito.<Boolean>any());
        verify(book4).setDescription(Mockito.<String>any());
        verify(book4).setDisabled(Mockito.<Boolean>any());
        verify(book4).setExpense(Mockito.<BigDecimal>any());
        verify(book4).setId(Mockito.<Long>any());
        verify(book4).setIncome(Mockito.<BigDecimal>any());
        verify(book4).setName(Mockito.<String>any());
        verify(book4).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book4).setDefaultAccount(anyLong());
        verify(book4).setUserId(Mockito.<Long>any());
        verify(transaction).getBook();
        verify(transaction).setAccount(Mockito.<Account>any());
        verify(transaction).setAmount(Mockito.<BigDecimal>any());
        verify(transaction).setBook(Mockito.<Book>any());
        verify(transaction).setCategory(Mockito.<Category>any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.<Map<String, Object>>any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.<String>any());
        verify(transaction).setTime(Mockito.<LocalDateTime>any());
        verify(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#add(AddTransaction)}
     */
    @Test
    void testAdd() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.get(Mockito.<Long>any())).thenReturn(account);
        when(currentSession.getBook()).thenThrow(new RuntimeException("Book not found"));

        AddTransaction addTransaction = new AddTransaction();
        addTransaction.setAccountId(1L);
        addTransaction.setAmount(new BigDecimal("2.3"));
        addTransaction.setCategoryId(1L);
        addTransaction.setNotes("Notes");
        addTransaction.setTime("Time");
        addTransaction.setType("Type");
        assertThrows(RuntimeException.class, () -> transactionService.add(addTransaction));
        verify(accountService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#add(AddTransaction)}
     */
    @Test
    void testAdd2() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        Book book2 = mock(Book.class);
        doNothing().when(book2).setBalance(Mockito.any());
        doNothing().when(book2).setCreateTime(Mockito.any());
        doNothing().when(book2).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book2).setDescription(Mockito.any());
        doNothing().when(book2).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book2).setExpense(Mockito.any());
        doNothing().when(book2).setId(Mockito.<Long>any());
        doNothing().when(book2).setIncome(Mockito.any());
        doNothing().when(book2).setName(Mockito.any());
        doNothing().when(book2).setUpdateTime(Mockito.any());
        doNothing().when(book2).setDefaultAccount(anyLong());
        doNothing().when(book2).setUserId(Mockito.<Long>any());
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);
        Account account = mock(Account.class);
        when(account.getBook()).thenReturn(book2);
        doNothing().when(account).setBook(Mockito.any());
        doNothing().when(account).setBalance(Mockito.any());
        doNothing().when(account).setCreateTime(Mockito.any());
        doNothing().when(account).setDeleted(Mockito.<Boolean>any());
        doNothing().when(account).setDescription(Mockito.any());
        doNothing().when(account).setDisabled(Mockito.<Boolean>any());
        doNothing().when(account).setExpense(Mockito.any());
        doNothing().when(account).setId(Mockito.<Long>any());
        doNothing().when(account).setIncome(Mockito.any());
        doNothing().when(account).setName(Mockito.any());
        doNothing().when(account).setUpdateTime(Mockito.any());
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.get(Mockito.<Long>any())).thenReturn(account);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book3);

        AddTransaction addTransaction = new AddTransaction();
        addTransaction.setAccountId(1L);
        addTransaction.setAmount(new BigDecimal("2.3"));
        addTransaction.setCategoryId(1L);
        addTransaction.setNotes("Notes");
        addTransaction.setTime("Time");
        addTransaction.setType("Type");
        assertThrows(RuntimeException.class, () -> transactionService.add(addTransaction));
        verify(accountService).get(Mockito.<Long>any());
        verify(account).getBook();
        verify(account).setBook(Mockito.any());
        verify(account).setBalance(Mockito.any());
        verify(account).setCreateTime(Mockito.any());
        verify(account).setDeleted(Mockito.<Boolean>any());
        verify(account).setDescription(Mockito.any());
        verify(account).setDisabled(Mockito.<Boolean>any());
        verify(account).setExpense(Mockito.any());
        verify(account).setId(Mockito.<Long>any());
        verify(account).setIncome(Mockito.any());
        verify(account).setName(Mockito.any());
        verify(account).setUpdateTime(Mockito.any());
        verify(book2).setBalance(Mockito.any());
        verify(book2).setCreateTime(Mockito.any());
        verify(book2).setDeleted(Mockito.<Boolean>any());
        verify(book2).setDescription(Mockito.any());
        verify(book2).setDisabled(Mockito.<Boolean>any());
        verify(book2).setExpense(Mockito.any());
        verify(book2).setId(Mockito.<Long>any());
        verify(book2).setIncome(Mockito.any());
        verify(book2).setName(Mockito.any());
        verify(book2).setUpdateTime(Mockito.any());
        verify(book2).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#add(AddTransaction)}
     */
    @Test
    void testAdd3() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        Book book2 = mock(Book.class);
        doNothing().when(book2).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book2).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book2).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book2).setDescription(Mockito.<String>any());
        doNothing().when(book2).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book2).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book2).setId(Mockito.<Long>any());
        doNothing().when(book2).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book2).setName(Mockito.<String>any());
        doNothing().when(book2).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book2).setDefaultAccount(anyLong());
        doNothing().when(book2).setUserId(Mockito.<Long>any());
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);
        Account account = mock(Account.class);
        when(account.getBook()).thenReturn(book2);
        doNothing().when(account).setBook(Mockito.<Book>any());
        doNothing().when(account).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(account).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(account).setDeleted(Mockito.<Boolean>any());
        doNothing().when(account).setDescription(Mockito.<String>any());
        doNothing().when(account).setDisabled(Mockito.<Boolean>any());
        doNothing().when(account).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(account).setId(Mockito.<Long>any());
        doNothing().when(account).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(account).setName(Mockito.<String>any());
        doNothing().when(account).setUpdateTime(Mockito.<LocalDateTime>any());
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.get(Mockito.<Long>any())).thenReturn(account);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book3);

        AddTransaction addTransaction = new AddTransaction();
        addTransaction.setAccountId(1L);
        addTransaction.setAmount(new BigDecimal("2.3"));
        addTransaction.setCategoryId(1L);
        addTransaction.setNotes("Notes");
        addTransaction.setTime("Time");
        addTransaction.setType("Type");
        assertThrows(RuntimeException.class, () -> transactionService.add(addTransaction));
        verify(accountService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(account).getBook();
        verify(account).setBook(Mockito.<Book>any());
        verify(account).setBalance(Mockito.<BigDecimal>any());
        verify(book2).setBalance(Mockito.<BigDecimal>any());
        verify(account).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book2).setCreateTime(Mockito.<LocalDateTime>any());
        verify(account).setDeleted(Mockito.<Boolean>any());
        verify(book2).setDeleted(Mockito.<Boolean>any());
        verify(account).setDescription(Mockito.<String>any());
        verify(book2).setDescription(Mockito.<String>any());
        verify(account).setDisabled(Mockito.<Boolean>any());
        verify(book2).setDisabled(Mockito.<Boolean>any());
        verify(account).setExpense(Mockito.<BigDecimal>any());
        verify(book2).setExpense(Mockito.<BigDecimal>any());
        verify(account).setId(Mockito.<Long>any());
        verify(book2).setId(Mockito.<Long>any());
        verify(account).setIncome(Mockito.<BigDecimal>any());
        verify(book2).setIncome(Mockito.<BigDecimal>any());
        verify(account).setName(Mockito.<String>any());
        verify(book2).setName(Mockito.<String>any());
        verify(account).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(book2).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(book2).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#update(long, AddTransaction)}
     */
    @Test
    void testUpdate() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(true);
        account2.setDescription("The characteristics of someone or something");
        account2.setDisabled(true);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(1L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("Name");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(3L);
        book6.setDeleted(true);
        book6.setDescription("The characteristics of someone or something");
        book6.setDisabled(true);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(1L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("Name");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(1L);

        Category category2 = new Category();
        category2.setBook(book6);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account2);
        transaction2.setAmount(new BigDecimal("2.3"));
        transaction2.setBook(book5);
        transaction2.setCategory(category2);
        transaction2.setDeleted(true);
        transaction2.setDetails(new HashMap<>());
        transaction2.setDisabled(true);
        transaction2.setId(1L);
        transaction2.setNotes("Notes");
        transaction2.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction2);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book7 = new Book();
        book7.setBalance(new BigDecimal("2.3"));
        book7.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book7.setDefaultAccount(3L);
        book7.setDeleted(true);
        book7.setDescription("The characteristics of someone or something");
        book7.setDisabled(true);
        book7.setExpense(new BigDecimal("2.3"));
        book7.setId(1L);
        book7.setIncome(new BigDecimal("2.3"));
        book7.setName("Name");
        book7.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book7.setUserId(1L);

        Account account3 = new Account();
        account3.setBalance(new BigDecimal("2.3"));
        account3.setBook(book7);
        account3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account3.setDeleted(true);
        account3.setDescription("The characteristics of someone or something");
        account3.setDisabled(true);
        account3.setExpense(new BigDecimal("2.3"));
        account3.setId(1L);
        account3.setIncome(new BigDecimal("2.3"));
        account3.setName("Name");
        account3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.get(Mockito.<Long>any())).thenReturn(account3);

        Book book8 = new Book();
        book8.setBalance(new BigDecimal("2.3"));
        book8.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book8.setDefaultAccount(3L);
        book8.setDeleted(true);
        book8.setDescription("The characteristics of someone or something");
        book8.setDisabled(true);
        book8.setExpense(new BigDecimal("2.3"));
        book8.setId(1L);
        book8.setIncome(new BigDecimal("2.3"));
        book8.setName("Name");
        book8.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book8.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book8);

        AddTransaction addTransaction = new AddTransaction();
        addTransaction.setAccountId(1L);
        addTransaction.setAmount(new BigDecimal("2.3"));
        addTransaction.setCategoryId(1L);
        addTransaction.setNotes("Notes");
        addTransaction.setTime("Time");
        addTransaction.setType("Type");
        transactionService.update(1L, addTransaction);
        verify(accountService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getBook();
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#update(long, AddTransaction)}
     */
    @Test
    void testUpdate2() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.save(Mockito.<Transaction>any())).thenThrow(new RuntimeException("foo"));
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(true);
        account2.setDescription("The characteristics of someone or something");
        account2.setDisabled(true);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(1L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("Name");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.get(Mockito.<Long>any())).thenReturn(account2);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book5);

        AddTransaction addTransaction = new AddTransaction();
        addTransaction.setAccountId(1L);
        addTransaction.setAmount(new BigDecimal("2.3"));
        addTransaction.setCategoryId(1L);
        addTransaction.setNotes("Notes");
        addTransaction.setTime("Time");
        addTransaction.setType("Type");
        assertThrows(RuntimeException.class, () -> transactionService.update(1L, addTransaction));
        verify(accountService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getBook();
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        doNothing().when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book4);
        transactionService.delete(1L);
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete2() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        doThrow(new RuntimeException("Name")).when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book4);
        assertThrows(RuntimeException.class, () -> transactionService.delete(1L));
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete3() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Book book4 = mock(Book.class);
        doNothing().when(book4).setBalance(Mockito.any());
        doNothing().when(book4).setCreateTime(Mockito.any());
        doNothing().when(book4).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book4).setDescription(Mockito.any());
        doNothing().when(book4).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book4).setExpense(Mockito.any());
        doNothing().when(book4).setId(Mockito.<Long>any());
        doNothing().when(book4).setIncome(Mockito.any());
        doNothing().when(book4).setName(Mockito.any());
        doNothing().when(book4).setUpdateTime(Mockito.any());
        doNothing().when(book4).setDefaultAccount(anyLong());
        doNothing().when(book4).setUserId(Mockito.<Long>any());
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getBook()).thenReturn(book4);
        doNothing().when(transaction).setAccount(Mockito.any());
        doNothing().when(transaction).setAmount(Mockito.any());
        doNothing().when(transaction).setBook(Mockito.any());
        doNothing().when(transaction).setCategory(Mockito.any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.any());
        doNothing().when(transaction).setTime(Mockito.any());
        doNothing().when(transaction).setUpdateTime(Mockito.any());
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book5);
        assertThrows(RuntimeException.class, () -> transactionService.delete(1L));
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transaction).getBook();
        verify(transaction).setAccount(Mockito.any());
        verify(transaction).setAmount(Mockito.any());
        verify(transaction).setBook(Mockito.any());
        verify(transaction).setCategory(Mockito.any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.any());
        verify(transaction).setTime(Mockito.any());
        verify(transaction).setUpdateTime(Mockito.any());
        verify(book4).setBalance(Mockito.any());
        verify(book4).setCreateTime(Mockito.any());
        verify(book4).setDeleted(Mockito.<Boolean>any());
        verify(book4).setDescription(Mockito.any());
        verify(book4).setDisabled(Mockito.<Boolean>any());
        verify(book4).setExpense(Mockito.any());
        verify(book4).setId(Mockito.<Long>any());
        verify(book4).setIncome(Mockito.any());
        verify(book4).setName(Mockito.any());
        verify(book4).setUpdateTime(Mockito.any());
        verify(book4).setDefaultAccount(anyLong());
        verify(book4).setUserId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete4() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        doNothing().when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book4);
        transactionService.delete(1L);
        verify(currentSession).getBook();
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete5() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        doThrow(new RuntimeException("foo")).when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book4);
        assertThrows(RuntimeException.class, () -> transactionService.delete(1L));
        verify(currentSession).getBook();
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete6() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);

        Category category2 = new Category();
        category2.setBook(book5);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getCategory()).thenReturn(category2);
        when(transaction.getAmount()).thenReturn(new BigDecimal("2.3"));
        when(transaction.getBook()).thenReturn(book4);
        doNothing().when(transaction).setAccount(Mockito.<Account>any());
        doNothing().when(transaction).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(transaction).setBook(Mockito.<Book>any());
        doNothing().when(transaction).setCategory(Mockito.<Category>any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.<Map<String, Object>>any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.<String>any());
        doNothing().when(transaction).setTime(Mockito.<LocalDateTime>any());
        doNothing().when(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        doNothing().when(transactionRepository).deleteById(Mockito.<Long>any());
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(3L);
        book6.setDeleted(true);
        book6.setDescription("The characteristics of someone or something");
        book6.setDisabled(true);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(1L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("Name");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book6);
        transactionService.delete(1L);
        verify(currentSession).getBook();
        verify(transaction).getAmount();
        verify(transaction).getBook();
        verify(transaction).getCategory();
        verify(transaction).setAccount(Mockito.<Account>any());
        verify(transaction).setAmount(Mockito.<BigDecimal>any());
        verify(transaction).setBook(Mockito.<Book>any());
        verify(transaction).setCategory(Mockito.<Category>any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.<Map<String, Object>>any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.<String>any());
        verify(transaction).setTime(Mockito.<LocalDateTime>any());
        verify(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#delete(Long)}
     */
    @Test
    void testDelete7() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Book book3 = new Book();
        book3.setBalance(new BigDecimal("2.3"));
        book3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setDefaultAccount(3L);
        book3.setDeleted(true);
        book3.setDescription("The characteristics of someone or something");
        book3.setDisabled(true);
        book3.setExpense(new BigDecimal("2.3"));
        book3.setId(1L);
        book3.setIncome(new BigDecimal("2.3"));
        book3.setName("Name");
        book3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book3.setUserId(1L);

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(3L);
        book4.setDeleted(true);
        book4.setDescription("The characteristics of someone or something");
        book4.setDisabled(true);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(1L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("Name");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(1L);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getCategory()).thenThrow(new RuntimeException("foo"));
        when(transaction.getBook()).thenReturn(book4);
        doNothing().when(transaction).setAccount(Mockito.<Account>any());
        doNothing().when(transaction).setAmount(Mockito.<BigDecimal>any());
        doNothing().when(transaction).setBook(Mockito.<Book>any());
        doNothing().when(transaction).setCategory(Mockito.<Category>any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.<Map<String, Object>>any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.<String>any());
        doNothing().when(transaction).setTime(Mockito.<LocalDateTime>any());
        doNothing().when(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book2);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Transaction> ofResult = Optional.of(transaction);
        when(transactionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(3L);
        book5.setDeleted(true);
        book5.setDescription("The characteristics of someone or something");
        book5.setDisabled(true);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(1L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("Name");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book5);
        assertThrows(RuntimeException.class, () -> transactionService.delete(1L));
        verify(currentSession).getBook();
        verify(transaction).getBook();
        verify(transaction).getCategory();
        verify(transaction).setAccount(Mockito.<Account>any());
        verify(transaction).setAmount(Mockito.<BigDecimal>any());
        verify(transaction).setBook(Mockito.<Book>any());
        verify(transaction).setCategory(Mockito.<Category>any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.<Map<String, Object>>any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.<String>any());
        verify(transaction).setTime(Mockito.<LocalDateTime>any());
        verify(transaction).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(transactionRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int)}
     */
    @Test
    void testGetPage() {
        PageImpl<Transaction> pageImpl = new PageImpl<>(new ArrayList<>());
        when(transactionRepository.findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);

        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        Page<Transaction> actualPage = transactionService.getPage(1, 3);
        verify(currentSession).getBook();
        verify(transactionRepository).findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any());
        assertTrue(actualPage.toList().isEmpty());
        assertSame(pageImpl, actualPage);
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int)}
     */
    @Test
    void testGetPage2() {
        when(transactionRepository.findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any()))
                .thenThrow(new RuntimeException("foo"));

        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        assertThrows(RuntimeException.class, () -> transactionService.getPage(1, 3));
        verify(currentSession).getBook();
        verify(transactionRepository).findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int)}
     */
    @Test
    void testGetPage3() {
        PageImpl<Transaction> pageImpl = new PageImpl<>(new ArrayList<>());
        when(transactionRepository.findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        Page<Transaction> actualPage = transactionService.getPage(1, 3);
        verify(currentSession).getBook();
        verify(book).getId();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
        verify(transactionRepository).findByBookId(Mockito.<Long>any(), Mockito.<Pageable>any());
        assertTrue(actualPage.toList().isEmpty());
        assertSame(pageImpl, actualPage);
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetPage4() {
        PageImpl<Transaction> pageImpl = new PageImpl<>(new ArrayList<>());
        when(transactionRepository.findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);

        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        LocalDateTime from = LocalDate.of(1970, 1, 1).atStartOfDay();
        Page<Transaction> actualPage = transactionService.getPage(1, 3, from, LocalDate.of(1970, 1, 1).atStartOfDay());
        verify(currentSession).getBook();
        verify(transactionRepository).findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any());
        assertTrue(actualPage.toList().isEmpty());
        assertSame(pageImpl, actualPage);
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetPage5() {
        when(transactionRepository.findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any())).thenThrow(new RuntimeException("foo"));

        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        LocalDateTime from = LocalDate.of(1970, 1, 1).atStartOfDay();
        assertThrows(RuntimeException.class,
                () -> transactionService.getPage(1, 3, from, LocalDate.of(1970, 1, 1).atStartOfDay()));
        verify(currentSession).getBook();
        verify(transactionRepository).findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link TransactionService#getPage(int, int, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetPage6() {
        PageImpl<Transaction> pageImpl = new PageImpl<>(new ArrayList<>());
        when(transactionRepository.findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any())).thenReturn(pageImpl);
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);
        LocalDateTime from = LocalDate.of(1970, 1, 1).atStartOfDay();
        Page<Transaction> actualPage = transactionService.getPage(1, 3, from, LocalDate.of(1970, 1, 1).atStartOfDay());
        verify(currentSession).getBook();
        verify(book).getId();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
        verify(transactionRepository).findByBookIdAndTimeBetween(Mockito.<Long>any(), Mockito.<LocalDateTime>any(),
                Mockito.<LocalDateTime>any(), Mockito.<Pageable>any());
        assertTrue(actualPage.toList().isEmpty());
        assertSame(pageImpl, actualPage);
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertSame(book2, transactionService.checkAccount(account));
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount2() {
        Book book = new Book();
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Book actualCheckAccountResult = transactionService.checkAccount(account);
        verify(currentSession).getBook();
        assertSame(book2, actualCheckAccountResult);
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount3() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(1L);
        when(book.getDefaultAccount()).thenReturn(3L);
        when(book.getId()).thenReturn(1L);

        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);


        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book2);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        Book actualCheckAccountResult = transactionService.checkAccount(account);
        verify(currentSession).getBook();
//        verify(book).getId();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

//        verify(book).getDefaultAccount();
//        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
        assertSame(book2, actualCheckAccountResult);
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount4() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenThrow(new RuntimeException("Book not found"));
        when(book.getDefaultAccount()).thenReturn(3L);
        when(book.getId()).thenReturn(1L);

        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertThrows(RuntimeException.class, () -> transactionService.checkAccount(account));
        verify(currentSession).getBook();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount5() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(3L);
        when(book.getDefaultAccount()).thenReturn(3L);
        when(book.getId()).thenReturn(1L);

        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertThrows(RuntimeException.class, () -> transactionService.checkAccount(account));
        verify(currentSession).getBook();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount6() {
        Book book = mock(Book.class);
        when(book.getDefaultAccount()).thenReturn(1L);
        when(book.getId()).thenReturn(1L);

        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertThrows(RuntimeException.class, () -> transactionService.checkAccount(account));
        verify(currentSession).getBook();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount7() {
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(3L);

        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertThrows(RuntimeException.class, () -> transactionService.checkAccount(account));
        verify(currentSession).getBook();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionService#checkAccount(Account)}
     */
    @Test
    void testCheckAccount8() {
        Book book = mock(Book.class);
        doNothing().when(book).setBalance(Mockito.<BigDecimal>any());
        doNothing().when(book).setCreateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.<String>any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.<BigDecimal>any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.<BigDecimal>any());
        doNothing().when(book).setName(Mockito.<String>any());
        doNothing().when(book).setUpdateTime(Mockito.<LocalDateTime>any());
        doNothing().when(book).setDefaultAccount(anyLong());
        doNothing().when(book).setUserId(Mockito.<Long>any());
        book.setBalance(new BigDecimal("2.3"));
        book.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setDefaultAccount(3L);
        book.setDeleted(true);
        book.setDescription("The characteristics of someone or something");
        book.setDisabled(true);
        book.setExpense(new BigDecimal("2.3"));
        book.setId(1L);
        book.setIncome(new BigDecimal("2.3"));
        book.setName("Name");
        book.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book.setUserId(1L);
        when(currentSession.getBook()).thenReturn(book);

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(3L);
        book2.setDeleted(true);
        book2.setDescription("The characteristics of someone or something");
        book2.setDisabled(true);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(1L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("Name");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(1L);

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book2);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        assertThrows(RuntimeException.class, () -> transactionService.checkAccount(account));
        verify(currentSession).getBook();
        verify(book).setBalance(Mockito.<BigDecimal>any());
        verify(book).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.<String>any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.<BigDecimal>any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.<BigDecimal>any());
        verify(book).setName(Mockito.<String>any());
        verify(book).setUpdateTime(Mockito.<LocalDateTime>any());

        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }
}