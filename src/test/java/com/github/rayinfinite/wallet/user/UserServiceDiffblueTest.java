package com.github.rayinfinite.wallet.user;

import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.AddUser;
import com.github.rayinfinite.wallet.model.user.dto.ChangePassword;
import com.github.rayinfinite.wallet.model.user.dto.ForgetPassword;
import com.github.rayinfinite.wallet.model.user.dto.Login;
import com.github.rayinfinite.wallet.model.user.dto.UpdateUser;
import com.github.rayinfinite.wallet.user.factory.LoginService;
import com.github.rayinfinite.wallet.user.factory.LoginServiceFactory;

import java.time.LocalDateTime;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
    @MockBean
    private LoginServiceFactory loginServiceFactory;

    @MockBean
    private LoginService loginService;

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
    void testLogin() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);

        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.any());
        doNothing().when(currentSession).setUser(Mockito.any());
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertSame(user, userService.login(login));
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
    void testLogin2() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.any())).thenReturn(ofResult);
        doThrow(new DefaultException("An error occurred")).when(currentSession).setUser(Mockito.any());
        when(passwordEncoder.matches(Mockito.any(), Mockito.any())).thenReturn(true);
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.login(login));
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        userService.login(login);
        verify(user).getPassword();
        verify(user).getDefaultBook();
        verify(user).setAvatar(Mockito.any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.any());
        verify(user).setPassword(Mockito.any());
        verify(user).setRegisterTime(Mockito.any());
        verify(user).setPhone(Mockito.any());
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
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);

        Login login = new Login();
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.login(login));
    }

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin5() {
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
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());

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
        LoginService loginService = mock(LoginService.class);
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        Login login = new Login();
        login.setLoginType(1);
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        User actualLoginResult = userService.login(login);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
        verify(loginService).login(Mockito.<Login>any());
        verify(loginServiceFactory).getLoginService(anyInt());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
        assertSame(user, actualLoginResult);
    }

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin6() {
        doThrow(new DefaultException("An error occurred")).when(currentSession).setUser(Mockito.<User>any());

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
        LoginService loginService = mock(LoginService.class);
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        Login login = new Login();
        login.setLoginType(1);
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.login(login));
        verify(currentSession).setUser(Mockito.<User>any());
        verify(loginService).login(Mockito.<Login>any());
        verify(loginServiceFactory).getLoginService(anyInt());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#login(Login)}
     */
    @Test
    void testLogin7() {
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
        when(currentSession.getBook()).thenReturn(book2);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getDefaultBook()).thenReturn(1L);
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
        LoginService loginService = mock(LoginService.class);
        when(loginService.login(Mockito.<Login>any())).thenReturn(user);
        when(loginServiceFactory.getLoginService(anyInt())).thenReturn(loginService);
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        Login login = new Login();
        login.setLoginType(1);
        login.setPassword("iloveyou");
        login.setUsername("janedoe");
        userService.login(login);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getBook();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
        verify(user).getDefaultBook();
        verify(user).getPassword();
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(loginService).login(Mockito.<Login>any());
        verify(loginServiceFactory).getLoginService(anyInt());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#register(AddUser)}
     */
    @Test
    void testRegister() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);

        AddUser addUser = new AddUser();
        addUser.setEmail("jane.doe@example.org");
        addUser.setNickName("Nick Name");
        addUser.setPassword("iloveyou");
        addUser.setPhone("6625550144");
        addUser.setUsername("janedoe");
        User actualRegisterResult = userService.register(addUser);
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        assertNull(actualRegisterResult);
    }

    /**
     * Method under test: {@link UserService#register(AddUser)}
     */
    @Test
    void testRegister2() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book2);
        when(bookService.add(Mockito.<AddBook>any())).thenReturn(book);

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
        when(currentSession.getUser()).thenReturn(user2);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        AddUser addUser = new AddUser();
        addUser.setEmail("jane.doe@example.org");
        addUser.setNickName("Nick Name");
        addUser.setPassword("iloveyou");
        addUser.setPhone("6625550144");
        addUser.setUsername("janedoe");
        User actualRegisterResult = userService.register(addUser);
        verify(bookService).add(Mockito.<AddBook>any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession, atLeast(1)).setBook(Mockito.<Book>any());
        verify(currentSession, atLeast(1)).setUser(Mockito.<User>any());
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        verify(userRepository, atLeast(1)).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertSame(user, actualRegisterResult);
    }

    /**
     * Method under test: {@link UserService#register(AddUser)}
     */
    @Test
    void testRegister3() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(emptyResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new DefaultException("An error occurred"));

        AddUser addUser = new AddUser();
        addUser.setEmail("jane.doe@example.org");
        addUser.setNickName("Nick Name");
        addUser.setPassword("iloveyou");
        addUser.setPhone("6625550144");
        addUser.setUsername("janedoe");
        assertThrows(DefaultException.class, () -> userService.register(addUser));
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserService#register(AddUser)}
     */
    @Test
    void testRegister4() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(book2.getUserId()).thenReturn(1L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book2);
        when(bookService.add(Mockito.<AddBook>any())).thenReturn(book);

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
        when(currentSession.getUser()).thenReturn(user2);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        AddUser addUser = new AddUser();
        addUser.setEmail("jane.doe@example.org");
        addUser.setNickName("Nick Name");
        addUser.setPassword("iloveyou");
        addUser.setPhone("6625550144");
        addUser.setUsername("janedoe");
        User actualRegisterResult = userService.register(addUser);
        verify(bookService).add(Mockito.<AddBook>any());
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession, atLeast(1)).setBook(Mockito.<Book>any());
        verify(currentSession, atLeast(1)).setUser(Mockito.<User>any());
        verify(book2).setBalance(Mockito.<BigDecimal>any());
        verify(book2).setCreateTime(Mockito.<LocalDateTime>any());
        verify(book2).setDeleted(Mockito.<Boolean>any());
        verify(book2).setDescription(Mockito.<String>any());
        verify(book2).setDisabled(Mockito.<Boolean>any());
        verify(book2).setExpense(Mockito.<BigDecimal>any());
        verify(book2).setId(Mockito.<Long>any());
        verify(book2).setIncome(Mockito.<BigDecimal>any());
        verify(book2).setName(Mockito.<String>any());
        verify(book2).setUpdateTime(Mockito.<LocalDateTime>any());
        verify(book2).getUserId();
        verify(book2).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        verify(userRepository, atLeast(1)).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertSame(user, actualRegisterResult);
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
        user.setPhone("6625550144");
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
        user2.setPhone("6625550144");
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
        user.setPhone("6625550144");
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
        user.setPhone("6625550144");
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
        doNothing().when(user2).setPhone(Mockito.any());
        doNothing().when(user2).setUsername(Mockito.any());
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setPhone("6625550144");
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
        verify(user2).setPhone(Mockito.any());
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        verify(user).setPhone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(passwordEncoder).matches(Mockito.any(), Mockito.any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword5() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

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
        when(currentSession.getUser()).thenReturn(user2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        userService.changePassword(changePassword);
        verify(currentSession).getUser();
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword6() {
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
        when(currentSession.getUser()).thenReturn(user);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new DefaultException("An error occurred"));
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        assertThrows(DefaultException.class, () -> userService.changePassword(changePassword));
        verify(currentSession).getUser();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword7() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        User user2 = mock(User.class);
        when(user2.getPassword()).thenReturn("iloveyou");
        doNothing().when(user2).setAvatar(Mockito.<String>any());
        doNothing().when(user2).setDefaultBook(anyLong());
        doNothing().when(user2).setEmail(Mockito.<String>any());
        doNothing().when(user2).setId(Mockito.<Long>any());
        doNothing().when(user2).setNickName(Mockito.<String>any());
        doNothing().when(user2).setPassword(Mockito.<String>any());
        doNothing().when(user2).setPhone(Mockito.<String>any());
        doNothing().when(user2).setRegisterTime(Mockito.<LocalDateTime>any());
        doNothing().when(user2).setUsername(Mockito.<String>any());
        user2.setAvatar("Avatar");
        user2.setDefaultBook(1L);
        user2.setEmail("jane.doe@example.org");
        user2.setId(1L);
        user2.setNickName("Nick Name");
        user2.setPassword("iloveyou");
        user2.setPhone("6625550144");
        user2.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUsername("janedoe");
        when(currentSession.getUser()).thenReturn(user2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        userService.changePassword(changePassword);
        verify(currentSession).getUser();
        verify(user2).getPassword();
        verify(user2).setAvatar(Mockito.<String>any());
        verify(user2).setDefaultBook(anyLong());
        verify(user2).setEmail(Mockito.<String>any());
        verify(user2).setId(Mockito.<Long>any());
        verify(user2).setNickName(Mockito.<String>any());
        verify(user2, atLeast(1)).setPassword(Mockito.<String>any());
        verify(user2).setPhone(Mockito.<String>any());
        verify(user2).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user2).setUsername(Mockito.<String>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
        assertEquals("iloveyou", changePassword.getNewPassword());
        assertEquals("iloveyou", changePassword.getOldPassword());
    }

    /**
     * Method under test: {@link UserService#changePassword(ChangePassword)}
     */
    @Test
    void testChangePassword8() {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
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
        when(currentSession.getUser()).thenReturn(user);
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(false);

        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword("iloveyou");
        changePassword.setOldPassword("iloveyou");
        assertThrows(DefaultException.class, () -> userService.changePassword(changePassword));
        verify(currentSession).getUser();
        verify(user).getPassword();
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
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
        user.setPhone("6625550144");
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        verify(user).setPhone(Mockito.any());
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        user2.setPhone("6625550144");
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
        verify(user).setPhone(Mockito.any());
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        verify(user).setPhone(Mockito.any());
        verify(user).setUsername(Mockito.any());
        verify(forgetPassword).validate(Mockito.any());
        verify(forgetPassword).getNewPassword();
        verify(forgetPassword).getUsername();
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword6() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(userRepository).findOneByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword7() {
        User user = mock(User.class);
        when(user.getNickName()).thenReturn("Nick Name");
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(user).getNickName();
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(userRepository).findOneByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword8() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> userService.forgetPassword(new ForgetPassword()));
        verify(userRepository).findOneByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword9() {
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
        Optional<User> ofResult = Optional.of(user);

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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        ForgetPassword forgetPassword = mock(ForgetPassword.class);
        when(forgetPassword.getNewPassword()).thenReturn("iloveyou");
        when(forgetPassword.validate(Mockito.<User>any())).thenReturn(true);
        when(forgetPassword.getUsername()).thenReturn("janedoe");
        userService.forgetPassword(forgetPassword);
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user, atLeast(1)).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(forgetPassword).getNewPassword();
        verify(forgetPassword).getUsername();
        verify(forgetPassword).validate(Mockito.<User>any());
        verify(userRepository).findOneByUsername(Mockito.<String>any());
        verify(userRepository).save(Mockito.<User>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link UserService#forgetPassword(ForgetPassword)}
     */
    @Test
    void testForgetPassword10() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findOneByUsername(Mockito.<String>any())).thenReturn(ofResult);
        ForgetPassword forgetPassword = mock(ForgetPassword.class);
        when(forgetPassword.getNewPassword()).thenThrow(new DefaultException("An error occurred"));
        when(forgetPassword.validate(Mockito.<User>any())).thenReturn(true);
        when(forgetPassword.getUsername()).thenReturn("janedoe");
        assertThrows(DefaultException.class, () -> userService.forgetPassword(forgetPassword));
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(forgetPassword).getNewPassword();
        verify(forgetPassword).getUsername();
        verify(forgetPassword).validate(Mockito.<User>any());
        verify(userRepository).findOneByUsername(Mockito.<String>any());
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
        user.setPhone("6625550144");
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
     * Method under test: {@link UserService#CurrentUser()}
     */
    @Test
    void testCurrentUser3() {
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
        when(currentSession.getUser()).thenReturn(user);
        User actualCurrentUserResult = userService.CurrentUser();
        verify(currentSession).getUser();
        assertSame(user, actualCurrentUserResult);
    }

    /**
     * Method under test: {@link UserService#update(UpdateUser)}
     */
    @Test
    void testUpdate() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

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
        when(currentSession.getUser()).thenReturn(user2);
        userService.update(new UpdateUser());
        verify(currentSession).getUser();
        verify(userRepository).save(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#update(UpdateUser)}
     */
    @Test
    void testUpdate2() {
        when(userRepository.save(Mockito.<User>any())).thenThrow(new DefaultException("An error occurred"));

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
        when(currentSession.getUser()).thenReturn(user);
        assertThrows(DefaultException.class, () -> userService.update(new UpdateUser()));
        verify(currentSession).getUser();
        verify(userRepository).save(Mockito.<User>any());
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
        user.setPhone("6625550144");
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
        user.setPhone("6625550144");
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
        doNothing().when(user).setPhone(Mockito.any());
        doNothing().when(user).setUsername(Mockito.any());
        user.setAvatar("Avatar");
        user.setDefaultBook(1L);
        user.setEmail("jane.doe@example.org");
        user.setId(1L);
        user.setNickName("Nick Name");
        user.setPassword("iloveyou");
        user.setRegisterTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setPhone("6625550144");
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
        verify(user).setPhone(Mockito.any());
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

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet5() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        User actualGetResult = userService.get(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
        assertSame(user, actualGetResult);
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet6() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doThrow(new DefaultException("An error occurred")).when(currentSession).setUser(Mockito.<User>any());
        assertThrows(DefaultException.class, () -> userService.get(1L));
        verify(currentSession).setUser(Mockito.<User>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#get(long)}
     */
    @Test
    void testGet7() {
        User user = mock(User.class);
        when(user.getDefaultBook()).thenReturn(1L);
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        userService.get(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
        verify(user).getDefaultBook();
        verify(user).setAvatar(Mockito.<String>any());
        verify(user).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
        verify(userRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#setDefaultBook(long)}
     */
    @Test
    void testSetDefaultBook() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);

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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(currentSession.getUser()).thenReturn(user2);
        User actualSetDefaultBookResult = userService.setDefaultBook(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
        verify(userRepository).save(Mockito.<User>any());
        assertSame(user, actualSetDefaultBookResult);
    }

    /**
     * Method under test: {@link UserService#setDefaultBook(long)}
     */
    @Test
    void testSetDefaultBook2() {
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doThrow(new DefaultException("An error occurred")).when(currentSession).setBook(Mockito.<Book>any());
        when(currentSession.getUser()).thenReturn(user);
        assertThrows(DefaultException.class, () -> userService.setDefaultBook(1L));
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
    }

    /**
     * Method under test: {@link UserService#setDefaultBook(long)}
     */
    @Test
    void testSetDefaultBook3() {
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
        when(userRepository.save(Mockito.<User>any())).thenReturn(user);
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(1L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(currentSession.getUser()).thenReturn(user2);
        User actualSetDefaultBookResult = userService.setDefaultBook(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
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
        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
        verify(userRepository).save(Mockito.<User>any());
        assertSame(user, actualSetDefaultBookResult);
    }

    /**
     * Method under test: {@link UserService#setDefaultBook(long)}
     */
    @Test
    void testSetDefaultBook4() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(0L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        when(currentSession.getUser()).thenReturn(user);
        assertThrows(DefaultException.class, () -> userService.setDefaultBook(1L));
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getUser();
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
        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#setBook(long)}
     */
    @Test
    void testSetBook() {
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(currentSession.getUser()).thenReturn(user);
        userService.setBook(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
    }

    /**
     * Method under test: {@link UserService#setBook(long)}
     */
    @Test
    void testSetBook2() {
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doThrow(new DefaultException("An error occurred")).when(currentSession).setBook(Mockito.<Book>any());
        when(currentSession.getUser()).thenReturn(user);
        assertThrows(DefaultException.class, () -> userService.setBook(1L));
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
    }

    /**
     * Method under test: {@link UserService#setBook(long)}
     */
    @Test
    void testSetBook3() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(1L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(currentSession.getUser()).thenReturn(user);
        userService.setBook(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
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
        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#setBook(long)}
     */
    @Test
    void testSetBook4() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(0L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);

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
        when(currentSession.getUser()).thenReturn(user);
        assertThrows(DefaultException.class, () -> userService.setBook(1L));
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getUser();
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
        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link UserService#setBook(long)}
     */
    @Test
    void testSetBook5() {
        Book book = mock(Book.class);
        when(book.getUserId()).thenReturn(1L);
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
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
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
        doNothing().when(currentSession).setBook(Mockito.<Book>any());
        doNothing().when(currentSession).setUser(Mockito.<User>any());
        when(currentSession.getUser()).thenReturn(user);
        userService.setBook(1L);
        verify(bookService).get(Mockito.<Long>any());
        verify(currentSession).getUser();
        verify(currentSession).setBook(Mockito.<Book>any());
        verify(currentSession).setUser(Mockito.<User>any());
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
        verify(book).getUserId();
        verify(book).setDefaultAccount(anyLong());
        verify(book).setUserId(Mockito.<Long>any());
        verify(user).getId();
        verify(user).setAvatar(Mockito.<String>any());
        verify(user, atLeast(1)).setDefaultBook(anyLong());
        verify(user).setEmail(Mockito.<String>any());
        verify(user).setId(Mockito.<Long>any());
        verify(user).setNickName(Mockito.<String>any());
        verify(user).setPassword(Mockito.<String>any());
        verify(user).setPhone(Mockito.<String>any());
        verify(user).setRegisterTime(Mockito.<LocalDateTime>any());
        verify(user).setUsername(Mockito.<String>any());
    }
}

