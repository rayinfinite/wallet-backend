package com.github.rayinfinite.wallet.user;

import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.ChangePassword;
import com.github.rayinfinite.wallet.model.user.dto.ForgetPassword;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
    @MockBean
    private BookService bookService;

    @MockBean
    private CurrentSession currentSession;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);

        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.any());
        doNothing().when(currentSession).setUser(Mockito.any());
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertSame(user, userService.login(login));
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(currentSession).setBook(Mockito.any());
        verify(currentSession).setUser(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
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
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin2() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        doThrow(new DefaultException("An error occurred")).when(currentSession).setUser(Mockito.any());
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.login(login));
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(currentSession).setUser(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin3() {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getDefaultBook()).thenReturn(1L);
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);

        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.any());
        doNothing().when(currentSession).setUser(Mockito.any());
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        userService.login(login);
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(user).getPassword();
        verify(user).getDefaultBook();
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(currentSession).setBook(Mockito.any());
        verify(currentSession).setUser(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin4() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(emptyResult);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.login(login));
        verify(userRepository).findOneByUsername(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword() {
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
        when(userRepository.save(Mockito.any())).thenReturn(user);

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
        when(currentSession.getUser()).thenReturn(user2);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        userService.changePassword(changePassword);
        verify(userRepository).save(Mockito.any());
        verify(currentSession).getUser();
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword2() {
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
        when(passwordEncoder.encode(Mockito.any())).thenThrow(new DefaultException("An error occurred"));
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        assertThrows(DefaultException.class, () -> userService.changePassword(changePassword));
        verify(currentSession).getUser();
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword3() {
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
        when(userRepository.save(Mockito.any())).thenReturn(user);
        User user2 = mock(User.class);
        when(user2.getPassword()).thenReturn("iloveyou");
        doNothing().when(user2).setAvatar(Mockito.any());
        doNothing().when(user2).setDefaultBook(anyLong());
        doNothing().when(user2).setEmail(Mockito.any());
        doNothing().when(user2).setId(Mockito.<Long>any());
        doNothing().when(user2).setNickName(Mockito.any());
        doNothing().when(user2).setPassword(Mockito.any());
        doNothing().when(user2).setRegisterTime(Mockito.any());
        doNothing().when(user2).setTelephone(Mockito.any());
        doNothing().when(user2).setUsername(Mockito.any());
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setTelephone("6625550144");
        user2.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user2);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        userService.changePassword(changePassword);
        verify(userRepository).save(Mockito.any());
        verify(currentSession).getUser();
        verify(user2).getPassword();
        verify(user2).setAvatar(Mockito.any());
        verify(user2).setDefaultBook(anyLong());
        verify(user2).setEmail(Mockito.any());
        verify(user2).setId(Mockito.<Long>any());
        verify(user2).setNickName(Mockito.any());
        verify(user2, atLeast(1)).setPassword(Mockito.any());
        verify(user2).setRegisterTime(Mockito.any());
        verify(user2).setTelephone(Mockito.any());
        verify(user2).setUsername(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
        assertEquals("iloveyou", changePassword.getNewPassword());
        assertEquals("iloveyou", changePassword.getOldPassword());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword4() {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
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
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(false);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        assertThrows(DefaultException.class, () -> userService.changePassword(changePassword));
        verify(currentSession).getUser();
        verify(user).getPassword();
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(userRepository).findOneByUsername(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword2() {
        User user = mock(User.class);
        when(user.getNickName()).thenReturn("Nick Name");
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(user).getNickName();
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword3() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(userRepository).findOneByUsername(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword4() {
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);

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
        when(userRepository.save(Mockito.any())).thenReturn(user2);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.any())).thenReturn("secret");
        ForgetPassword forgetPassword = mock(ForgetPassword.class);
        when(forgetPassword.getNewPassword()).thenReturn("iloveyou");
        when(forgetPassword.validate(Mockito.any())).thenReturn(true);
        when(forgetPassword.getUsername()).thenReturn("janedoe");
        userService.forgetPassword(forgetPassword);
        verify(userRepository).save(Mockito.any());
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user, atLeast(1)).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(passwordEncoder).encode(Mockito.any());
        verify(forgetPassword).validate(Mockito.any());
        verify(forgetPassword).getNewPassword();
        verify(forgetPassword).getUsername();
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword5() {
        User user = mock(User.class);
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        ForgetPassword forgetPassword = mock(ForgetPassword.class);
        when(forgetPassword.getNewPassword()).thenThrow(new DefaultException("An error occurred"));
        when(forgetPassword.validate(Mockito.any())).thenReturn(true);
        when(forgetPassword.getUsername()).thenReturn("janedoe");
        assertThrows(DefaultException.class, () -> userService.forgetPassword(forgetPassword));
        verify(userRepository).findOneByUsername(Mockito.any());
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(forgetPassword).validate(Mockito.any());
        verify(forgetPassword).getNewPassword();
        verify(forgetPassword).getUsername();
    }

    /**
     * Method under test: {@link UserService#CurrentUser()}
     */
    @Test
    void testCurrentUser() {
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
        assertSame(user, userService.CurrentUser());
        verify(currentSession).getUser();
    }

    /**
     * Method under test: {@link UserService#CurrentUser()}
     */
    @Test
    void testCurrentUser2() {
        when(currentSession.getUser()).thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> userService.CurrentUser());
        verify(currentSession).getUser();
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        doNothing().when(currentSession).setBook(Mockito.any());
        doNothing().when(currentSession).setUser(Mockito.any());
        assertSame(user, userService.get(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).setBook(Mockito.any());
        verify(currentSession).setUser(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet2() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doThrow(new DefaultException("An error occurred")).when(currentSession).setUser(Mockito.any());
        assertThrows(DefaultException.class, () -> userService.get(1L));
        verify(userRepository).findById(Mockito.<Long>any());
        verify(currentSession).setUser(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet3() {
        User user = mock(User.class);
        when(user.getDefaultBook()).thenReturn(1L);
        doNothing().when(user).setAvatar(Mockito.any());
        doNothing().when(user).setDefaultBook(anyLong());
        doNothing().when(user).setEmail(Mockito.any());
        doNothing().when(user).setId(Mockito.<Long>any());
        doNothing().when(user).setNickName(Mockito.any());
        doNothing().when(user).setPassword(Mockito.any());
        doNothing().when(user).setRegisterTime(Mockito.any());
        doNothing().when(user).setTelephone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setTelephone("6625550144");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        doNothing().when(currentSession).setBook(Mockito.any());
        doNothing().when(currentSession).setUser(Mockito.any());
        userService.get(1L);
        verify(userRepository).findById(Mockito.<Long>any());
        verify(user).getDefaultBook();
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setTelephone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).setBook(Mockito.any());
        verify(currentSession).setUser(Mockito.any());
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet4() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> userService.get(1L));
        verify(userRepository).findById(Mockito.<Long>any());
    }
}

