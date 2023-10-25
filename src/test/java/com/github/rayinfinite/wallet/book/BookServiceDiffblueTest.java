package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.UserBookRelation.UserBookRelationService;
import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.category.CategoryService;
import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.UserBookRelation;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {BookService.class})
@ExtendWith(SpringExtension.class)
class BookServiceDiffblueTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CurrentSession currentSession;

    @MockBean
    private UserBookRelationService userBookRelationService;

    /**
     * Method under test: {@link BookService#add(AddBook)}
     */
    @Test
    void testAdd() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.any());
        when(currentSession.getUser()).thenReturn(user);

        Book book3 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book3);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.add(Mockito.any(), Mockito.any(), anyInt())).thenReturn(userBookRelation);

        Book book4 = getBook();

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book4);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.add(Mockito.any())).thenReturn(account);
        doNothing().when(categoryService).init();
        assertSame(book, bookService.add(new AddBook()));
        verify(bookRepository, atLeast(1)).save(Mockito.any());
        verify(currentSession).getBook();
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.any());
        verify(userBookRelationService).add(Mockito.any(), Mockito.any(), anyInt());
        verify(accountService).add(Mockito.any());
        verify(categoryService).init();
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
     * Method under test: {@link BookService#add(AddBook)}
     */
    @Test
    void testAdd2() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        doNothing().when(currentSession).setBook(Mockito.any());
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.add(Mockito.any(), Mockito.any(), anyInt()))
                .thenReturn(userBookRelation);

        Book book3 = getBook();

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book3);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.add(Mockito.any())).thenReturn(account);
        doThrow(new DefaultException("An error occurred")).when(categoryService).init();
        assertThrows(DefaultException.class, () -> bookService.add(new AddBook()));
        verify(bookRepository).save(Mockito.any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.any());
        verify(userBookRelationService).add(Mockito.any(), Mockito.any(), anyInt());
        verify(accountService).add(Mockito.any());
        verify(categoryService).init();
    }

    /**
     * Method under test: {@link BookService#add(AddBook)}
     */
    @Test
    void testAdd3() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
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
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.any());
        when(currentSession.getUser()).thenReturn(user);

        Book book3 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book3);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.add(Mockito.any(), Mockito.any(), anyInt()))
                .thenReturn(userBookRelation);

        Book book4 = getBook();

        Account account = new Account();
        account.setBalance(new BigDecimal("2.3"));
        account.setBook(book4);
        account.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account.setDeleted(true);
        account.setDescription("The characteristics of someone or something");
        account.setDisabled(true);
        account.setExpense(new BigDecimal("2.3"));
        account.setId(1L);
        account.setIncome(new BigDecimal("2.3"));
        account.setName("Name");
        account.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(accountService.add(Mockito.any())).thenReturn(account);
        doNothing().when(categoryService).init();
        assertSame(book, bookService.add(new AddBook()));
        verify(bookRepository, atLeast(1)).save(Mockito.any());
        verify(currentSession).getBook();
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.any());
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
        verify(book2, atLeast(1)).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
        verify(userBookRelationService).add(Mockito.any(), Mockito.any(), anyInt());
        verify(accountService).add(Mockito.any());
        verify(categoryService).init();
    }

    /**
     * Method under test: {@link BookService#delete(Long)}
     */
    @Test
    void testDelete() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        doNothing().when(bookRepository).deleteById(Mockito.<Long>any());
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        doNothing().when(userBookRelationService).deleteByBook(Mockito.any());
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        bookService.delete(1L);
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(bookRepository).deleteById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
        verify(userBookRelationService).deleteByBook(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#delete(Long)}
     */
    @Test
    void testDelete2() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        doThrow(new DefaultException("An error occurred")).when(userBookRelationService)
                .deleteByBook(Mockito.any());
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        assertThrows(DefaultException.class, () -> bookService.delete(1L));
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
        verify(userBookRelationService).deleteByBook(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#delete(Long)}
     */
    @Test
    void testDelete3() {
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> bookService.delete(1L));
        verify(bookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BookService#update(long, AddBook)}
     */
    @Test
    void testUpdate() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);

        Book book2 = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book2);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book3 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book3);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        bookService.update(1L, new AddBook());
        verify(bookRepository).save(Mockito.any());
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#update(long, AddBook)}
     */
    @Test
    void testUpdate2() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        assertThrows(DefaultException.class, () -> bookService.update(1L, new AddBook()));
        verify(bookRepository).save(Mockito.any());
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#update(long, AddBook)}
     */
    @Test
    void testUpdate3() {
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> bookService.update(1L, new AddBook()));
        verify(bookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BookService#save(Book)}
     */
    @Test
    void testSave() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book book2 = getBook();
        bookService.save(book2);
        verify(bookRepository).save(Mockito.any());
        BigDecimal expectedBalance = new BigDecimal("2.3");
        assertEquals(expectedBalance, book2.getBalance());
        assertEquals(1L, book2.getUserId().longValue());
        assertEquals("Name", book2.getName());
        assertEquals("00:00", book2.getUpdateTime().toLocalTime().toString());
        BigDecimal expectedIncome = new BigDecimal("2.3");
        assertEquals(expectedIncome, book2.getIncome());
        assertEquals(1L, book2.getId().longValue());
        BigDecimal expectedExpense = new BigDecimal("2.3");
        assertEquals(expectedExpense, book2.getExpense());
        assertTrue(book2.getDisabled());
        assertEquals("The characteristics of someone or something", book2.getDescription());
        assertTrue(book2.getDeleted());
        assertEquals(3L, book2.getDefaultAccount());
        assertEquals("1970-01-01", book2.getCreateTime().toLocalDate().toString());
    }

    /**
     * Method under test: {@link BookService#save(Book)}
     */
    @Test
    void testSave2() {
        when(bookRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        assertThrows(DefaultException.class, () -> bookService.save(book));
        verify(bookRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#get(Long)}
     */
    @Test
    void testGet() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        assertSame(book, bookService.get(1L));
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#get(Long)}
     */
    @Test
    void testGet2() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> bookService.get(1L));
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#get(Long)}
     */
    @Test
    void testGet3() {
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> bookService.get(1L));
        verify(bookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BookService#get(Long, int[])}
     */
    @Test
    void testGet4() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);

        Book book2 = getBook();

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book2);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user2);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenReturn(userBookRelation);
        assertSame(book, bookService.get(1L, new int[]{1, 0, 1, 0}));
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#get(Long, int[])}
     */
    @Test
    void testGet5() {
        Book book = getBook();
        Optional<Book> ofResult = Optional.of(book);
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);
        when(userBookRelationService.findByUserAndBook(Mockito.any(), Mockito.any()))
                .thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> bookService.get(1L, new int[]{1, 0, 1, 0}));
        verify(bookRepository).findById(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUserAndBook(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link BookService#get(Long, int[])}
     */
    @Test
    void testGet6() {
        Optional<Book> emptyResult = Optional.empty();
        when(bookRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> bookService.get(1L, new int[]{1, 0, 1, 0}));
        verify(bookRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BookService#setDefaultAccount(long)}
     */
    @Test
    void testSetDefaultAccount() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertSame(book, bookService.setDefaultAccount(1L));
        verify(bookRepository).save(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link BookService#setDefaultAccount(long)}
     */
    @Test
    void testSetDefaultAccount2() {
        when(bookRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        assertThrows(DefaultException.class, () -> bookService.setDefaultAccount(1L));
        verify(bookRepository).save(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link BookService#setDefaultAccount(long)}
     */
    @Test
    void testSetDefaultAccount3() {
        Book book = getBook();
        when(bookRepository.save(Mockito.any())).thenReturn(book);
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
        when(currentSession.getBook()).thenReturn(book2);
        assertSame(book, bookService.setDefaultAccount(1L));
        verify(bookRepository).save(Mockito.any());
        verify(currentSession).getBook();
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
        verify(book2, atLeast(1)).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link BookService#list()}
     */
    @Test
    void testList() {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);
        when(userBookRelationService.findByUser(Mockito.any())).thenReturn(new ArrayList<>());
        assertTrue(bookService.list().isEmpty());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUser(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#list()}
     */
    @Test
    void testList2() {
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user);
        when(userBookRelationService.findByUser(Mockito.any()))
                .thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> bookService.list());
        verify(currentSession).getUser();
        verify(userBookRelationService).findByUser(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#getUser(Book)}
     */
    @Test
    void testGetUser() {
        when(userBookRelationService.findByBook(Mockito.any())).thenReturn(new ArrayList<>());

        Book book = getBook();
        assertTrue(bookService.getUser(book).isEmpty());
        verify(userBookRelationService).findByBook(Mockito.any());
    }

    /**
     * Method under test: {@link BookService#getUser(Book)}
     */
    @Test
    void testGetUser2() {
        when(userBookRelationService.findByBook(Mockito.any()))
                .thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        assertThrows(DefaultException.class, () -> bookService.getUser(book));
        verify(userBookRelationService).findByBook(Mockito.any());
    }
}

