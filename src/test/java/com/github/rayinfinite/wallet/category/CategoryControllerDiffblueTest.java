package com.github.rayinfinite.wallet.category;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerDiffblueTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryController#get(long)}
     */
    @Test
    void testGet() throws Exception {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        when(categoryService.get(Mockito.<Long>any())).thenReturn(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/{id}", 1L);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":0,\"data\":{\"id\":1,\"name\":\"Name\",\"icon\":\"Icon\",\"type\":1,\"child\":[]},\"message\":\"ok\"}"));
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
     * Method under test: {@link CategoryController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        when(categoryService.list()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":0,\"data\":[],\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#add(long, Category)}
     */
    @Test
    void testAdd() throws Exception {
        doNothing().when(categoryService).add(anyLong(), Mockito.any());

        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        String content = (new ObjectMapper()).writeValueAsString(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/add/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"code\":0,\"data\":\"Add successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#delete(long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(categoryService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/category/{id}", 1L);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#delete(long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(categoryService).delete(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/category/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Delete successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#update(long, Category)}
     */
    @Test
    void testUpdate() throws Exception {
        doNothing().when(categoryService).update(Mockito.<Long>any(), Mockito.any());

        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        String content = (new ObjectMapper()).writeValueAsString(category);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/category/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Update successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#init()}
     */
    @Test
    void testInit() throws Exception {
        doNothing().when(categoryService).init();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/init");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Initialize successfully\",\"message\":\"ok\"}"));
    }

    /**
     * Method under test: {@link CategoryController#init()}
     */
    @Test
    void testInit2() throws Exception {
        doNothing().when(categoryService).init();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/category/init");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"code\":0,\"data\":\"Initialize successfully\",\"message\":\"ok\"}"));
    }
}

