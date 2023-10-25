package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import com.github.rayinfinite.wallet.model.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AccountService.class})
@ExtendWith(SpringExtension.class)
class AccountServiceDiffblueTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @MockBean
    private CurrentSession currentSession;

    /**
     * Method under test: {@link AccountService#get(Long)}
     */
    @Test
    void testGet() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertSame(account, accountService.get(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    private static Book getBook() {
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
        return book;
    }

    /**
     * Method under test: {@link AccountService#get(Long)}
     */
    @Test
    void testGet2() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(currentSession.getBook()).thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> accountService.get(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#get(Long)}
     */
    @Test
    void testGet3() {
        Book book = getBook();

        Book book2 = getBook();
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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        accountService.get(1L);
        verify(accountRepository).findById(Mockito.<Long>any());
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
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#get(Long)}
     */
    @Test
    void testGet4() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(1L);
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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        accountService.get(1L);
        verify(accountRepository).findById(Mockito.<Long>any());
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
        verify(book2).getId();
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
     * Method under test: {@link AccountService#get(Long)}
     */
    @Test
    void testGet5() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(0L);
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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        assertThrows(DefaultException.class, () -> accountService.get(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
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
        verify(book2).getId();
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
     * Method under test: {@link AccountService#add(AddAccount)}
     */
    @Test
    void testAdd() {
        Book book = getBook();

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
        when(accountRepository.save(Mockito.any())).thenReturn(account);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertSame(account, accountService.add(new AddAccount()));
        verify(accountRepository).save(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#add(AddAccount)}
     */
    @Test
    void testAdd2() {
        when(currentSession.getBook()).thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> accountService.add(new AddAccount()));
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#update(long, AddAccount)}
     */
    @Test
    void testUpdate() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);

        Book book2 = getBook();

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book2);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(true);
        account2.setDescription("The characteristics of someone or something");
        account2.setDisabled(true);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(1L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("Name");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountRepository.save(Mockito.any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        accountService.update(1L, new AddAccount());
        verify(accountRepository).save(Mockito.any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#update(long, AddAccount)}
     */
    @Test
    void testUpdate2() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertThrows(DefaultException.class, () -> accountService.update(1L, new AddAccount()));
        verify(accountRepository).save(Mockito.any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#update(long, AddAccount)}
     */
    @Test
    void testUpdate3() {
        Book book = getBook();

        Book book2 = getBook();
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
        Optional<Account> ofResult = Optional.of(account);

        Book book3 = getBook();

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book3);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(true);
        account2.setDescription("The characteristics of someone or something");
        account2.setDisabled(true);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(1L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("Name");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountRepository.save(Mockito.any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book4 = getBook();
        when(currentSession.getBook()).thenReturn(book4);
        accountService.update(1L, new AddAccount());
        verify(accountRepository).save(Mockito.any());
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(account).getBook();
        verify(account).setBook(Mockito.any());
        verify(account, atLeast(1)).setBalance(Mockito.any());
        verify(account).setCreateTime(Mockito.any());
        verify(account, atLeast(1)).setDeleted(Mockito.<Boolean>any());
        verify(account, atLeast(1)).setDescription(Mockito.any());
        verify(account, atLeast(1)).setDisabled(Mockito.<Boolean>any());
        verify(account, atLeast(1)).setExpense(Mockito.any());
        verify(account).setId(Mockito.<Long>any());
        verify(account, atLeast(1)).setIncome(Mockito.any());
        verify(account, atLeast(1)).setName(Mockito.any());
        verify(account, atLeast(1)).setUpdateTime(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#save(Account)}
     */
    @Test
    void testSave() {
        Book book = getBook();

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
        when(accountRepository.save(Mockito.any())).thenReturn(account);

        Book book2 = getBook();

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book2);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(true);
        account2.setDescription("The characteristics of someone or something");
        account2.setDisabled(true);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(1L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("Name");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        accountService.save(account2);
        verify(accountRepository).save(Mockito.any());
        BigDecimal expectedBalance = new BigDecimal("2.3");
        assertEquals(expectedBalance, account2.getBalance());
        assertEquals("Name", account2.getName());
        assertEquals("00:00", account2.getUpdateTime().toLocalTime().toString());
        BigDecimal expectedIncome = new BigDecimal("2.3");
        assertEquals(expectedIncome, account2.getIncome());
        assertEquals(1L, account2.getId().longValue());
        BigDecimal expectedExpense = new BigDecimal("2.3");
        assertEquals(expectedExpense, account2.getExpense());
        assertTrue(account2.getDisabled());
        assertEquals("The characteristics of someone or something", account2.getDescription());
        assertTrue(account2.getDeleted());
        assertEquals(book, account2.getBook());
        assertEquals("00:00", account2.getCreateTime().toLocalTime().toString());
    }

    /**
     * Method under test: {@link AccountService#delete(Long)}
     */
    @Test
    void testDelete() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        accountService.delete(1L);
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(accountRepository).deleteById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#delete(Long)}
     */
    @Test
    void testDelete2() {
        Book book = getBook();

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
        Optional<Account> ofResult = Optional.of(account);
        doThrow(new DefaultException("An error occurred")).when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertThrows(DefaultException.class, () -> accountService.delete(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(accountRepository).deleteById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#delete(Long)}
     */
    @Test
    void testDelete3() {
        Book book = getBook();

        Book book2 = getBook();
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
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        accountService.delete(1L);
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(accountRepository).deleteById(Mockito.<Long>any());
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
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#delete(Long)}
     */
    @Test
    void testDelete4() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(1L);
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
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        accountService.delete(1L);
        verify(accountRepository).findById(Mockito.<Long>any());
        verify(accountRepository).deleteById(Mockito.<Long>any());
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
        verify(book2).getId();
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
     * Method under test: {@link AccountService#delete(Long)}
     */
    @Test
    void testDelete5() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(0L);
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
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        assertThrows(DefaultException.class, () -> accountService.delete(1L));
        verify(accountRepository).findById(Mockito.<Long>any());
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
        verify(book2).getId();
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
     * Method under test: {@link AccountService#getPage(String, int, int)}
     */
    @Test
    void testGetPage() {
        PageImpl<Account> pageImpl = new PageImpl<>(new ArrayList<>());
        when(accountRepository.findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any())).thenReturn(pageImpl);

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        Page<Account> actualPage = accountService.getPage("Keyword", 1, 3);
        assertSame(pageImpl, actualPage);
        assertTrue(actualPage.toList().isEmpty());
        verify(accountRepository).findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#getPage(String, int, int)}
     */
    @Test
    void testGetPage2() {
        when(accountRepository.findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any())).thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        assertThrows(DefaultException.class, () -> accountService.getPage("Keyword", 1, 3));
        verify(accountRepository).findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#getPage(String, int, int)}
     */
    @Test
    void testGetPage3() {
        PageImpl<Account> pageImpl = new PageImpl<>(new ArrayList<>());
        when(accountRepository.findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any())).thenReturn(pageImpl);
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        doNothing().when(book).setBalance(Mockito.any());
        doNothing().when(book).setCreateTime(Mockito.any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.any());
        doNothing().when(book).setName(Mockito.any());
        doNothing().when(book).setUpdateTime(Mockito.any());
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
        Page<Account> actualPage = accountService.getPage("Keyword", 1, 3);
        assertSame(pageImpl, actualPage);
        assertTrue(actualPage.toList().isEmpty());
        verify(accountRepository).findByNameContainingAndBookId(Mockito.any(), Mockito.<Long>any(),
                Mockito.any());
        verify(currentSession).getBook();
        verify(book).getId();
        verify(book).setBalance(Mockito.any());
        verify(book).setCreateTime(Mockito.any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.any());
        verify(book).setName(Mockito.any());
        verify(book).setUpdateTime(Mockito.any());
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getPage(String, int, int)}
     */
    @Test
    void testGetPage4() {
        PageImpl<Account> pageImpl = new PageImpl<>(new ArrayList<>());
        when(accountRepository.findByBookId(Mockito.<Long>any(), Mockito.any())).thenReturn(pageImpl);
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        doNothing().when(book).setBalance(Mockito.any());
        doNothing().when(book).setCreateTime(Mockito.any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.any());
        doNothing().when(book).setName(Mockito.any());
        doNothing().when(book).setUpdateTime(Mockito.any());
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
        Page<Account> actualPage = accountService.getPage(null, 1, 3);
        assertSame(pageImpl, actualPage);
        assertTrue(actualPage.toList().isEmpty());
        verify(accountRepository).findByBookId(Mockito.<Long>any(), Mockito.any());
        verify(currentSession).getBook();
        verify(book).getId();
        verify(book).setBalance(Mockito.any());
        verify(book).setCreateTime(Mockito.any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.any());
        verify(book).setName(Mockito.any());
        verify(book).setUpdateTime(Mockito.any());
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link AccountService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findByBookId(Mockito.<Long>any())).thenReturn(accountList);

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        List<Account> actualAll = accountService.getAll();
        assertSame(accountList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(accountRepository).findByBookId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#getAll()}
     */
    @Test
    void testGetAll2() {
        when(accountRepository.findByBookId(Mockito.<Long>any())).thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        assertThrows(DefaultException.class, () -> accountService.getAll());
        verify(accountRepository).findByBookId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link AccountService#getAll()}
     */
    @Test
    void testGetAll3() {
        ArrayList<Account> accountList = new ArrayList<>();
        when(accountRepository.findByBookId(Mockito.<Long>any())).thenReturn(accountList);
        Book book = mock(Book.class);
        when(book.getId()).thenReturn(1L);
        doNothing().when(book).setBalance(Mockito.any());
        doNothing().when(book).setCreateTime(Mockito.any());
        doNothing().when(book).setDeleted(Mockito.<Boolean>any());
        doNothing().when(book).setDescription(Mockito.any());
        doNothing().when(book).setDisabled(Mockito.<Boolean>any());
        doNothing().when(book).setExpense(Mockito.any());
        doNothing().when(book).setId(Mockito.<Long>any());
        doNothing().when(book).setIncome(Mockito.any());
        doNothing().when(book).setName(Mockito.any());
        doNothing().when(book).setUpdateTime(Mockito.any());
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
        List<Account> actualAll = accountService.getAll();
        assertSame(accountList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(accountRepository).findByBookId(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(book).getId();
        verify(book).setBalance(Mockito.any());
        verify(book).setCreateTime(Mockito.any());
        verify(book).setDeleted(Mockito.<Boolean>any());
        verify(book).setDescription(Mockito.any());
        verify(book).setDisabled(Mockito.<Boolean>any());
        verify(book).setExpense(Mockito.any());
        verify(book).setId(Mockito.<Long>any());
        verify(book).setIncome(Mockito.any());
        verify(book).setName(Mockito.any());
        verify(book).setUpdateTime(Mockito.any());
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }
}

