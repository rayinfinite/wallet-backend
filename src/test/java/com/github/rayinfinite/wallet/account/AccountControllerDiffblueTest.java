package com.github.rayinfinite.wallet.account;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import com.github.rayinfinite.wallet.model.book.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
class AccountControllerDiffblueTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    /**
     * Method under test: {@link AccountController#get(long)}
     */
    @Test
    void testGet() throws Exception {
        Account account = getAccount();
        when(accountService.get(Mockito.<Long>any())).thenReturn(account);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/{id}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"createTime\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled\":true,\"income\":2.3,"
                                        + "\"expense\":2.3,\"balance\":2.3,\"book\":1},\"message\":\"ok\"}"));
    }

    private static Account getAccount() {
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
        return account;
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
     * Method under test: {@link AccountController#add(AddAccount)}
     */
    @Test
    void testAdd() throws Exception {
        Account account = getAccount();
        when(accountService.add(Mockito.any())).thenReturn(account);

        AddAccount addAccount = new AddAccount();
        addAccount.setBalance(new BigDecimal("2.3"));
        addAccount.setDeleted(true);
        addAccount.setDescription("The characteristics of someone or something");
        addAccount.setDisabled(true);
        addAccount.setExpense(new BigDecimal("2.3"));
        addAccount.setIncome(new BigDecimal("2.3"));
        addAccount.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(addAccount);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#delete(long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(accountService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/account/{id}", 1L);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#delete(long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(accountService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/account/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#update(long, AddAccount)}
     */
    @Test
    void testUpdate() throws Exception {
        doNothing().when(accountService).update(anyLong(), Mockito.any());

        AddAccount addAccount = new AddAccount();
        addAccount.setBalance(new BigDecimal("2.3"));
        addAccount.setDeleted(true);
        addAccount.setDescription("The characteristics of someone or something");
        addAccount.setDisabled(true);
        addAccount.setExpense(new BigDecimal("2.3"));
        addAccount.setIncome(new BigDecimal("2.3"));
        addAccount.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(addAccount);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#update(long, AddAccount)}
     */
    @Test
    void testUpdate2() throws Exception {
        Account account = getAccount();
        when(accountService.add(Mockito.any())).thenReturn(account);
        doNothing().when(accountService).update(anyLong(), Mockito.any());

        AddAccount addAccount = new AddAccount();
        addAccount.setBalance(new BigDecimal("2.3"));
        addAccount.setDeleted(true);
        addAccount.setDescription("The characteristics of someone or something");
        addAccount.setDisabled(true);
        addAccount.setExpense(new BigDecimal("2.3"));
        addAccount.setIncome(new BigDecimal("2.3"));
        addAccount.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(addAccount);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/account/{id}", "", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#getPage(String, int, int)}
     */
    @Test
    void testGetPage() throws Exception {
        when(accountService.getPage(Mockito.any(), anyInt(), anyInt()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/account/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1)).param("name", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":0,\"totalPages\":1,"
                                        + "\"numberOfElements\":0,\"first\":true,\"size\":0,\"number\":0,\"sort\":{\"empty\":true,\"unsorted\":true,\"sorted\""
                                        + ":false},\"empty\":true},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#getPage(String, int, int)}
     */
    @Test
    void testGetPage2() throws Exception {
        Account account = getAccount();

        ArrayList<Account> content = new ArrayList<>();
        content.add(account);
        PageImpl<Account> pageImpl = new PageImpl<>(content);
        when(accountService.getPage(Mockito.any(), anyInt(), anyInt())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/account/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1)).param("name", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"createTime\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled\":true,"
                                        + "\"income\":2.3,\"expense\":2.3,\"balance\":2.3,\"book\":1}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements"
                                        + "\":1,\"totalPages\":1,\"numberOfElements\":1,\"first\":true,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"unsorted"
                                        + "\":true,\"sorted\":false},\"empty\":false},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(accountService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link AccountController#getAll()}
     */
    @Test
    void testGetAll2() throws Exception {
        Account account = getAccount();

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountService.getAll()).thenReturn(accountList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/account/");
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":[{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"createTime\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled\":true,\"income\":2.3,"
                                        + "\"expense\":2.3,\"balance\":2.3,\"book\":1}],\"message\":\"ok\"}"));
    }
}

