package com.github.rayinfinite.wallet.transaction;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.AddTransaction;
import com.github.rayinfinite.wallet.model.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerDiffblueTest {
    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link TransactionController#get(long)}
     */
    @Test
    void testGet() throws Exception {
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
        when(transactionService.get(Mockito.<Long>any())).thenReturn(transaction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/transaction/{id}", 1L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"amount\":2.3,\"category\":{\"id\":1,\"name\":\"Name\",\"icon\":\"Icon\",\"type\":1,\"child"
                                        + "\":[]},\"account\":1,\"book\":1,\"time\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled"
                                        + "\":true,\"notes\":\"Notes\",\"details\":{}},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#getRangePage(int, int, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetRangePage() throws Exception {
        when(transactionService.getPage(anyInt(), anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/transaction/range");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("end",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult3.param("start",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":0,"
                                        + "\"size\":0,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements"
                                        + "\":0,\"empty\":true},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#getRangePage(int, int, LocalDateTime, LocalDateTime)}
     */
    @Test
    void testGetRangePage2() throws Exception {
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

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/transaction/range");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1));
        MockHttpServletRequestBuilder paramResult2 = paramResult.param("end",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockHttpServletRequestBuilder paramResult3 = paramResult2.param("pageSize", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult3.param("start",
                String.valueOf(LocalDate.of(1970, 1, 1).atStartOfDay()));
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[{\"id\":1,\"amount\":2.3,\"category\":{\"id\":1,\"name\":\"Name\",\"icon\":\"Icon\",\"type"
                                        + "\":1,\"child\":[]},\"account\":1,\"book\":1,\"time\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true"
                                        + ",\"disabled\":true,\"notes\":\"Notes\",\"details\":{}}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,"
                                        + "\"totalElements\":1,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\""
                                        + ":true,\"numberOfElements\":1,\"empty\":false},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#add(AddTransaction)}
     */
    @Test
    void testAdd() throws Exception {
        doNothing().when(transactionService).add(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction/");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#add(AddTransaction)}
     */
    @Test
    void testAdd2() throws Exception {
        doNothing().when(transactionService).add(Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction/");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#update(long, AddTransaction)}
     */
    @Test
    void testUpdate() throws Exception {
        doNothing().when(transactionService).update(anyLong(), Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction/{id}", 1L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#update(long, AddTransaction)}
     */
    @Test
    void testUpdate2() throws Exception {
        doNothing().when(transactionService).add(Mockito.any());
        doNothing().when(transactionService).update(anyLong(), Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/transaction/{id}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#delete(long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(transactionService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/transaction/{id}", 1L);
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#delete(long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(transactionService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/transaction/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#getPage(int, int)}
     */
    @Test
    void testGetPage() throws Exception {
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/transaction/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,\"totalElements\":0,"
                                        + "\"size\":0,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements"
                                        + "\":0,\"empty\":true},\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link TransactionController#getPage(int, int)}
     */
    @Test
    void testGetPage2() throws Exception {
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

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(pageImpl);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/transaction/page");
        MockHttpServletRequestBuilder paramResult = getResult.param("current", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(transactionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .json(
                                "{\"code\":0,\"data\":{\"content\":[{\"id\":1,\"amount\":2.3,\"category\":{\"id\":1,\"name\":\"Name\",\"icon\":\"Icon\",\"type"
                                        + "\":1,\"child\":[]},\"account\":1,\"book\":1,\"time\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true"
                                        + ",\"disabled\":true,\"notes\":\"Notes\",\"details\":{}}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalPages\":1,"
                                        + "\"totalElements\":1,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\""
                                        + ":true,\"numberOfElements\":1,\"empty\":false},\"message\":\"ok\"}"));
    }
}

