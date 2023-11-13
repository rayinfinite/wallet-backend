package com.github.rayinfinite.wallet.UserBookRelation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.UserBookRelation;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserBookRelationService.class})
@ExtendWith(SpringExtension.class)
class UserBookRelationServiceDiffblueTest {
    @MockBean
    private UserBookRelationRepository userBookRelationRepository;

    @Autowired
    private UserBookRelationService userBookRelationService;

    /**
     * Method under test: {@link UserBookRelationService#deleteByBook(Book)}
     */
    @Test
    void testDeleteByBook() {
        doNothing().when(userBookRelationRepository).deleteByBook(Mockito.any());

        Book book = getBook();
        userBookRelationService.deleteByBook(book);
        verify(userBookRelationRepository).deleteByBook(Mockito.any());
        BigDecimal expectedBalance = new BigDecimal("2.3");
        assertEquals(expectedBalance, book.getBalance());
        assertEquals(1L, book.getUserId().longValue());
        assertEquals("Name", book.getName());
        assertEquals("00:00", book.getUpdateTime().toLocalTime().toString());
        BigDecimal expectedIncome = new BigDecimal("2.3");
        assertEquals(expectedIncome, book.getIncome());
        assertEquals(1L, book.getId().longValue());
        BigDecimal expectedExpense = new BigDecimal("2.3");
        assertEquals(expectedExpense, book.getExpense());
        assertTrue(book.getDisabled());
        assertEquals("The characteristics of someone or something", book.getDescription());
        assertTrue(book.getDeleted());
        assertEquals(3L, book.getDefaultAccount());
        assertEquals("1970-01-01", book.getCreateTime().toLocalDate().toString());
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
     * Method under test: {@link UserBookRelationService#deleteByUser(User)}
     */
    @Test
    void testDeleteByUser() {
        doNothing().when(userBookRelationRepository).deleteByUser(Mockito.any());

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
        user.setUsername("janedoe");
        userBookRelationService.deleteByUser(user);
        verify(userBookRelationRepository).deleteByUser(Mockito.any());
        assertEquals("Avatar", user.getAvatar());
        assertEquals("janedoe", user.getUsername());
        assertEquals("6625550144", user.getPhone());
        assertEquals("iloveyou", user.getPassword());
        assertEquals("00:00", user.getRegisterTime().toLocalTime().toString());
        assertEquals("Nick Name", user.getNickName());
        assertEquals(1L, user.getId().longValue());
        assertEquals("jane.doe@example.org", user.getEmail());
        assertEquals(1L, user.getDefaultBook());
    }

    /**
     * Method under test: {@link UserBookRelationService#add(User, Book, int)}
     */
    @Test
    void testAdd() {
        Book book = getBook();

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);
        when(userBookRelationRepository.save(Mockito.any())).thenReturn(userBookRelation);

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setPhone("6625550144");
        user2.setUsername("janedoe");

        Book book2 = getBook();
        assertSame(userBookRelation, userBookRelationService.add(user2, book2, 2));
        verify(userBookRelationRepository).save(Mockito.any());
    }

    /**
     * Method under test: {@link UserBookRelationService#findByUser(User)}
     */
    @Test
    void testFindByUser() {
        ArrayList<UserBookRelation> userBookRelationList = new ArrayList<>();
        when(userBookRelationRepository.findByUser(Mockito.any())).thenReturn(userBookRelationList);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
        user.setUsername("janedoe");
        List<UserBookRelation> actualFindByUserResult = userBookRelationService.findByUser(user);
        assertSame(userBookRelationList, actualFindByUserResult);
        assertTrue(actualFindByUserResult.isEmpty());
        verify(userBookRelationRepository).findByUser(Mockito.any());
    }

    /**
     * Method under test: {@link UserBookRelationService#findByBook(Book)}
     */
    @Test
    void testFindByBook() {
        ArrayList<UserBookRelation> userBookRelationList = new ArrayList<>();
        when(userBookRelationRepository.findByBook(Mockito.any())).thenReturn(userBookRelationList);

        Book book = getBook();
        List<UserBookRelation> actualFindByBookResult = userBookRelationService.findByBook(book);
        assertSame(userBookRelationList, actualFindByBookResult);
        assertTrue(actualFindByBookResult.isEmpty());
        verify(userBookRelationRepository).findByBook(Mockito.any());
    }

    /**
     * Method under test: {@link UserBookRelationService#findByUserAndRole(User, Integer)}
     */
    @Test
    void testFindByUserAndRole() {
        ArrayList<UserBookRelation> userBookRelationList = new ArrayList<>();
        when(userBookRelationRepository.findByUserAndRole(Mockito.any(), Mockito.<Integer>any()))
                .thenReturn(userBookRelationList);

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
        user.setUsername("janedoe");
        List<UserBookRelation> actualFindByUserAndRoleResult = userBookRelationService.findByUserAndRole(user, 1);
        assertSame(userBookRelationList, actualFindByUserAndRoleResult);
        assertTrue(actualFindByUserAndRoleResult.isEmpty());
        verify(userBookRelationRepository).findByUserAndRole(Mockito.any(), Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link UserBookRelationService#findByUserAndBook(User, Book)}
     */
    @Test
    void testFindByUserAndBook() {
        Book book = getBook();

        User user = new User();
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
        user.setUsername("janedoe");

        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setId(1);
        userBookRelation.setRole(1);
        userBookRelation.setUser(user);
        Optional<UserBookRelation> ofResult = Optional.of(userBookRelation);
        when(userBookRelationRepository.findByUserAndBook(Mockito.any(), Mockito.any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setPhone("6625550144");
        user2.setUsername("janedoe");

        Book book2 = getBook();
        assertSame(userBookRelation, userBookRelationService.findByUserAndBook(user2, book2));
        verify(userBookRelationRepository).findByUserAndBook(Mockito.any(), Mockito.any());
    }
}

