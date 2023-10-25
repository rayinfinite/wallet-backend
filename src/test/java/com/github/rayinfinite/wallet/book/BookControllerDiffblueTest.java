package com.github.rayinfinite.wallet.book;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.book.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookController.class})
@ExtendWith(SpringExtension.class)
class BookControllerDiffblueTest {
    @Autowired
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @MockBean
    private ReportService reportService;

    /**
     * Method under test: {@link BookController#get(long)}
     */
    @Test
    void testGet() throws Exception {
        Book book = getBook();
        when(bookService.get(Mockito.<Long>any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/{id}", 1L);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"createTime\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled\":true,\"income\":2.3,"
                                        + "\"expense\":2.3,\"balance\":2.3,\"userId\":1,\"defaultAccount\":3},\"message\":\"ok\"}"));
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
     * Method under test: {@link BookController#add(AddBook)}
     */
    @Test
    void testAdd() throws Exception {
        Book book = getBook();
        when(bookService.add(Mockito.any())).thenReturn(book);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#delete(long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(bookService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/book/{id}", 1L);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#delete(long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(bookService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/book/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#update(long, AddBook)}
     */
    @Test
    void testUpdate() throws Exception {
        doNothing().when(bookService).update(anyLong(), Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/{id}", 1L);
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#update(long, AddBook)}
     */
    @Test
    void testUpdate2() throws Exception {
        Book book = getBook();
        when(bookService.add(Mockito.any())).thenReturn(book);
        doNothing().when(bookService).update(anyLong(), Mockito.any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/{id}", "", "Uri Variables");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#list()}
     */
    @Test
    void testList() throws Exception {
        when(bookService.list()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/list");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#list()}
     */
    @Test
    void testList2() throws Exception {
        Book book = getBook();

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookService.list()).thenReturn(bookList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/list");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":[{\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"createTime\":[1970,1,1,0,0],\"updateTime\":[1970,1,1,0,0],\"deleted\":true,\"disabled\":true,\"income\":2.3,"
                                        + "\"expense\":2.3,\"balance\":2.3,\"userId\":1,\"defaultAccount\":3}],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#getUser(Book)}
     */
    @Test
    void testGetUser() throws Exception {
        when(bookService.getUser(Mockito.any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book/user");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#overview()}
     */
    @Test
    void testOverview() throws Exception {
        when(reportService.overview()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/overview");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#reportBalance()}
     */
    @Test
    void testReportBalance() throws Exception {
        when(reportService.reportBalance()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/reportBalance");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory() throws Exception {
        when(reportService.reportExpenseCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/reportExpenseCategory");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link BookController#reportIncomeCategory()}
     */
    @Test
    void testReportIncomeCategory() throws Exception {
        when(reportService.reportIncomeCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book/reportIncomeCategory");
        MockMvcBuilders.standaloneSetup(bookController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }
}

