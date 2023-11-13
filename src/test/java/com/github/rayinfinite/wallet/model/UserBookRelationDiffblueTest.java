package com.github.rayinfinite.wallet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {UserBookRelation.class})
@ExtendWith(SpringExtension.class)
class UserBookRelationDiffblueTest {
    @Autowired
    private UserBookRelation userBookRelation;

    /**
     * Method under test: {@link UserBookRelation#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new UserBookRelation()).canEqual("Other"));
    }

    /**
     * Method under test: {@link UserBookRelation#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        UserBookRelation userBookRelation2 = new UserBookRelation();

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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(3L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation3 = new UserBookRelation();
        userBookRelation3.setBook(book);
        userBookRelation3.setId(1);
        userBookRelation3.setRole(3);
        userBookRelation3.setUser(user);
        assertTrue(userBookRelation2.canEqual(userBookRelation3));
    }

    /**
     * Method under test: {@link UserBookRelation#canEqual(Object)}
     */
    @Test
    void testCanEqual3() {
        UserBookRelation userBookRelation2 = new UserBookRelation();
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(3L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation3 = new UserBookRelation();
        userBookRelation3.setBook(book);
        userBookRelation3.setId(1);
        userBookRelation3.setRole(3);
        userBookRelation3.setUser(user);
        boolean actualCanEqualResult = userBookRelation2.canEqual(userBookRelation3);
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
        assertTrue(actualCanEqualResult);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link UserBookRelation}
     *   <li>{@link UserBookRelation#setBook(Book)}
     *   <li>{@link UserBookRelation#setId(Integer)}
     *   <li>{@link UserBookRelation#setRole(Integer)}
     *   <li>{@link UserBookRelation#setUser(User)}
     *   <li>{@link UserBookRelation#toString()}
     *   <li>{@link UserBookRelation#getBook()}
     *   <li>{@link UserBookRelation#getId()}
     *   <li>{@link UserBookRelation#getRole()}
     *   <li>{@link UserBookRelation#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserBookRelation actualUserBookRelation = new UserBookRelation();
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
        actualUserBookRelation.setBook(book);
        actualUserBookRelation.setId(1);
        actualUserBookRelation.setRole(1);
        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");
        actualUserBookRelation.setUser(user);
        String actualToStringResult = actualUserBookRelation.toString();
        Book actualBook = actualUserBookRelation.getBook();
        Integer actualId = actualUserBookRelation.getId();
        Integer actualRole = actualUserBookRelation.getRole();
        User actualUser = actualUserBookRelation.getUser();
        assertEquals("UserBookRelation(id=1, user=User(id=1, username=janedoe, password=iloveyou, nickName=Nick Name,"
                + " avatar=Avatar, phone=6625550144, email=jane.doe@example.org, registerTime=1970-01-01T00:00, defaultBook=1),"
                + " book=Book(userId=1, defaultAccount=3), role=1)", actualToStringResult);
        assertEquals(1, actualId.intValue());
        assertEquals(1, actualRole.intValue());
        assertSame(book, actualBook);
        assertSame(user, actualUser);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);
        assertNotEquals(userBookRelation, null);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals2() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);
        assertNotEquals(userBookRelation, "Different type to UserBookRelation");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserBookRelation#equals(Object)}
     *   <li>{@link UserBookRelation#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);
        assertEquals(userBookRelation, userBookRelation);
        int expectedHashCodeResult = userBookRelation.hashCode();
        assertEquals(expectedHashCodeResult, userBookRelation.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserBookRelation#equals(Object)}
     *   <li>{@link UserBookRelation#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertEquals(userBookRelation, userBookRelation2);
        int expectedHashCodeResult = userBookRelation.hashCode();
        assertEquals(expectedHashCodeResult, userBookRelation2.hashCode());
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals5() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals6() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(2);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals7() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(null);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals8() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(3);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals9() {
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

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(null);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }

    /**
     * Method under test: {@link UserBookRelation#equals(Object)}
     */
    @Test
    void testEquals10() {
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
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.<String>any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setPhone(Mockito.<String>any());
        doNothing().when(user).setRegisterTime(Mockito.<LocalDateTime>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setPhone("6625550144");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);

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

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");

        UserBookRelation userBookRelation2 = new UserBookRelation();
        userBookRelation2.setBook(book2);
        userBookRelation2.setId(1);
        userBookRelation2.setRole(1);
        userBookRelation2.setUser(user2);
        assertNotEquals(userBookRelation, userBookRelation2);
    }
}
