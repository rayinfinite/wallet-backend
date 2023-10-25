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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
        doNothing().when(transactionRepository).deleteById(Mockito.<Long>any());
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
        transactionService.delete(1L);
        verify(transactionRepository).findById(Mockito.<Long>any());
        verify(transactionRepository).deleteById(Mockito.<Long>any());
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
}