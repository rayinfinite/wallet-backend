package com.github.rayinfinite.wallet.transaction.observer;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ObserverBook.class})
@ExtendWith(SpringExtension.class)
class ObserverBookDiffblueTest {
    @MockBean
    private BookService bookService;

    @Autowired
    private ObserverBook observerBook;

    /**
     * Method under test: {@link ObserverBook#transaction(Transaction, Category, BigDecimal)}
     */
    @Test
    void testTransaction() {
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
        doNothing().when(bookService).save(Mockito.<Book>any());
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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

        Category category = new Category();
        category.setBook(book4);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(new BigDecimal("2.3"));
        transaction.setBook(book3);
        transaction.setCategory(category);
        transaction.setDeleted(true);
        transaction.setDetails(new HashMap<>());
        transaction.setDisabled(true);
        transaction.setId(1L);
        transaction.setNotes("Notes");
        transaction.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

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
        observerBook.transaction(transaction, category2, new BigDecimal("2.3"));
        verify(bookService).get(Mockito.<Long>any());
        verify(bookService).save(Mockito.<Book>any());
    }
}
