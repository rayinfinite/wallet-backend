package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.model.ChartVO;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.transaction.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ReportService.class})
@ExtendWith(SpringExtension.class)
class ReportServiceDiffblueTest {
    @MockBean
    private BookService bookService;

    @Autowired
    private ReportService reportService;

    @MockBean
    private TransactionService transactionService;

    /**
     * Method under test: {@link ReportService#overview()}
     */
    @Test
    void testOverview() {
        when(bookService.list()).thenReturn(new ArrayList<>());
        assertEquals(3, reportService.overview().size());
        verify(bookService).list();
    }

    /**
     * Method under test: {@link ReportService#overview()}
     */
    @Test
    void testOverview2() {
        Book book = getBook();

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookService.list()).thenReturn(bookList);
        assertEquals(3, reportService.overview().size());
        verify(bookService).list();
    }

    /**
     * Method under test: {@link ReportService#reportBalance()}
     */
    @Test
    void testReportBalance() {
        when(bookService.list()).thenReturn(new ArrayList<>());
        assertEquals(2, reportService.reportBalance().size());
        verify(bookService).list();
    }

    /**
     * Method under test: {@link ReportService#reportBalance()}
     */
    @Test
    void testReportBalance2() {
        Book book = getBook();

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookService.list()).thenReturn(bookList);
        List<List<ChartVO>> actualReportBalanceResult = reportService.reportBalance();
        assertEquals(2, actualReportBalanceResult.size());
        List<ChartVO> getResult = actualReportBalanceResult.get(0);
        assertEquals(1, getResult.size());
        List<ChartVO> getResult2 = actualReportBalanceResult.get(1);
        assertEquals(1, getResult2.size());
        BigDecimal expectedY = new BigDecimal("2.3");
        ChartVO getResult3 = getResult.get(0);
        assertEquals(expectedY, getResult3.getY());
        BigDecimal expectedY2 = new BigDecimal("2.3");
        ChartVO getResult4 = getResult2.get(0);
        assertEquals(expectedY2, getResult4.getY());
        assertEquals("Name", getResult4.getX());
        assertEquals("Name", getResult3.getX());
        verify(bookService).list();
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
     * Method under test: {@link ReportService#reportBalance()}
     */
    @Test
    void testReportBalance3() {
        Book book = getBook();

        Book book2 = new Book();
        book2.setBalance(new BigDecimal("2.3"));
        book2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setDefaultAccount(1L);
        book2.setDeleted(false);
        book2.setDescription("Description");
        book2.setDisabled(false);
        book2.setExpense(new BigDecimal("2.3"));
        book2.setId(2L);
        book2.setIncome(new BigDecimal("2.3"));
        book2.setName("com.github.rayinfinite.wallet.model.book.Book");
        book2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book2.setUserId(2L);

        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book2);
        bookList.add(book);
        when(bookService.list()).thenReturn(bookList);
        List<List<ChartVO>> actualReportBalanceResult = reportService.reportBalance();
        assertEquals(2, actualReportBalanceResult.size());
        List<ChartVO> getResult = actualReportBalanceResult.get(0);
        assertEquals(2, getResult.size());
        List<ChartVO> getResult2 = actualReportBalanceResult.get(1);
        assertEquals(2, getResult2.size());
        BigDecimal expectedY = new BigDecimal("2.3");
        ChartVO getResult3 = getResult.get(0);
        assertEquals(expectedY, getResult3.getY());
        BigDecimal expectedY2 = new BigDecimal("2.3");
        ChartVO getResult4 = getResult2.get(0);
        assertEquals(expectedY2, getResult4.getY());
        assertEquals("com.github.rayinfinite.wallet.model.book.Book", getResult4.getX());
        assertEquals("com.github.rayinfinite.wallet.model.book.Book", getResult3.getX());
        ChartVO getResult5 = getResult2.get(1);
        assertEquals("Name", getResult5.getX());
        ChartVO getResult6 = getResult.get(1);
        assertEquals("Name", getResult6.getX());
        BigDecimal expectedY3 = new BigDecimal("2.3");
        assertEquals(expectedY3, getResult5.getY());
        BigDecimal expectedY4 = new BigDecimal("2.3");
        assertEquals(expectedY4, getResult6.getY());
        verify(bookService).list();
    }

    /**
     * Method under test: {@link ReportService#reportCategory(int)}
     */
    @Test
    void testReportCategory() {
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(reportService.reportCategory(1).isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportCategory(int)}
     */
    @Test
    void testReportCategory2() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

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
        assertTrue(reportService.reportCategory(1).isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
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

    /**
     * Method under test: {@link ReportService#reportCategory(int)}
     */
    @Test
    void testReportCategory3() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

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

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(100000L);
        book4.setDeleted(false);
        book4.setDescription("Description");
        book4.setDisabled(false);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(2L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("com.github.rayinfinite.wallet.model.book.Book");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(2L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(false);
        account2.setDescription("Description");
        account2.setDisabled(false);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(2L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("com.github.rayinfinite.wallet.model.account.Account");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(100000L);
        book5.setDeleted(false);
        book5.setDescription("Description");
        book5.setDisabled(false);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(2L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("com.github.rayinfinite.wallet.model.book.Book");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(2L);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(100000L);
        book6.setDeleted(false);
        book6.setDescription("Description");
        book6.setDisabled(false);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(2L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("com.github.rayinfinite.wallet.model.book.Book");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(2L);

        Category category2 = new Category();
        category2.setBook(book6);
        category2.setChild(new ArrayList<>());
        category2.setIcon("com.github.rayinfinite.wallet.model.category.Category");
        category2.setId(2L);
        category2.setName("com.github.rayinfinite.wallet.model.category.Category");
        category2.setType(1);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account2);
        transaction2.setAmount(new BigDecimal("2.3"));
        transaction2.setBook(book5);
        transaction2.setCategory(category2);
        transaction2.setDeleted(false);
        transaction2.setDetails(new HashMap<>());
        transaction2.setDisabled(false);
        transaction2.setId(2L);
        transaction2.setNotes("com.github.rayinfinite.wallet.model.transaction.Transaction");
        transaction2.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction2);
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(pageImpl);
        List<ChartVO> actualReportCategoryResult = reportService.reportCategory(1);
        assertEquals(1, actualReportCategoryResult.size());
        ChartVO getResult = actualReportCategoryResult.get(0);
        assertEquals("com.github.rayinfinite.wallet.model.category.Category", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportCategory(int)}
     */
    @Test
    void testReportCategory4() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

        Book book4 = getBook();

        Category category2 = new Category();
        category2.setBook(book4);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(new BigDecimal("2.3"));
        when(transaction.getCategory()).thenReturn(category2);
        doNothing().when(transaction).setAccount(Mockito.any());
        doNothing().when(transaction).setAmount(Mockito.any());
        doNothing().when(transaction).setBook(Mockito.any());
        doNothing().when(transaction).setCategory(Mockito.any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.any());
        doNothing().when(transaction).setTime(Mockito.any());
        doNothing().when(transaction).setUpdateTime(Mockito.any());
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
        List<ChartVO> actualReportCategoryResult = reportService.reportCategory(1);
        assertEquals(1, actualReportCategoryResult.size());
        ChartVO getResult = actualReportCategoryResult.get(0);
        assertEquals("Name", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
        verify(transaction, atLeast(1)).getCategory();
        verify(transaction).getAmount();
        verify(transaction).setAccount(Mockito.any());
        verify(transaction).setAmount(Mockito.any());
        verify(transaction).setBook(Mockito.any());
        verify(transaction).setCategory(Mockito.any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.any());
        verify(transaction).setTime(Mockito.any());
        verify(transaction).setUpdateTime(Mockito.any());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory() {
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(reportService.reportExpenseCategory().isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory2() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

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
        assertTrue(reportService.reportExpenseCategory().isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory3() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

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

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(100000L);
        book4.setDeleted(false);
        book4.setDescription("Description");
        book4.setDisabled(false);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(2L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("com.github.rayinfinite.wallet.model.book.Book");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(2L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(false);
        account2.setDescription("Description");
        account2.setDisabled(false);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(2L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("com.github.rayinfinite.wallet.model.account.Account");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(100000L);
        book5.setDeleted(false);
        book5.setDescription("Description");
        book5.setDisabled(false);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(2L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("com.github.rayinfinite.wallet.model.book.Book");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(2L);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(100000L);
        book6.setDeleted(false);
        book6.setDescription("Description");
        book6.setDisabled(false);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(2L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("com.github.rayinfinite.wallet.model.book.Book");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(2L);

        Category category2 = new Category();
        category2.setBook(book6);
        category2.setChild(new ArrayList<>());
        category2.setIcon("com.github.rayinfinite.wallet.model.category.Category");
        category2.setId(2L);
        category2.setName("com.github.rayinfinite.wallet.model.category.Category");
        category2.setType(1);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account2);
        transaction2.setAmount(new BigDecimal("2.3"));
        transaction2.setBook(book5);
        transaction2.setCategory(category2);
        transaction2.setDeleted(false);
        transaction2.setDetails(new HashMap<>());
        transaction2.setDisabled(false);
        transaction2.setId(2L);
        transaction2.setNotes("com.github.rayinfinite.wallet.model.transaction.Transaction");
        transaction2.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction2);
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(pageImpl);
        assertTrue(reportService.reportExpenseCategory().isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory4() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

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

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(100000L);
        book4.setDeleted(false);
        book4.setDescription("Description");
        book4.setDisabled(false);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(2L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("com.github.rayinfinite.wallet.model.book.Book");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(2L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(false);
        account2.setDescription("Description");
        account2.setDisabled(false);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(2L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("com.github.rayinfinite.wallet.model.account.Account");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(100000L);
        book5.setDeleted(false);
        book5.setDescription("Description");
        book5.setDisabled(false);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(2L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("com.github.rayinfinite.wallet.model.book.Book");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(2L);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(100000L);
        book6.setDeleted(false);
        book6.setDescription("Description");
        book6.setDisabled(false);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(2L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("com.github.rayinfinite.wallet.model.book.Book");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(2L);

        Category category2 = new Category();
        category2.setBook(book6);
        category2.setChild(new ArrayList<>());
        category2.setIcon("com.github.rayinfinite.wallet.model.category.Category");
        category2.setId(2L);
        category2.setName("com.github.rayinfinite.wallet.model.category.Category");
        category2.setType(1);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account2);
        transaction2.setAmount(new BigDecimal("2.3"));
        transaction2.setBook(book5);
        transaction2.setCategory(category2);
        transaction2.setDeleted(false);
        transaction2.setDetails(new HashMap<>());
        transaction2.setDisabled(false);
        transaction2.setId(2L);
        transaction2.setNotes("com.github.rayinfinite.wallet.model.transaction.Transaction");
        transaction2.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book7 = new Book();
        book7.setBalance(new BigDecimal("2.3"));
        book7.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book7.setDefaultAccount(1L);
        book7.setDeleted(true);
        book7.setDescription("com.github.rayinfinite.wallet.model.book.Book");
        book7.setDisabled(true);
        book7.setExpense(new BigDecimal("2.3"));
        book7.setId(3L);
        book7.setIncome(new BigDecimal("2.3"));
        book7.setName("42");
        book7.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book7.setUserId(3L);

        Account account3 = new Account();
        account3.setBalance(new BigDecimal("2.3"));
        account3.setBook(book7);
        account3.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account3.setDeleted(true);
        account3.setDescription("com.github.rayinfinite.wallet.model.account.Account");
        account3.setDisabled(true);
        account3.setExpense(new BigDecimal("2.3"));
        account3.setId(3L);
        account3.setIncome(new BigDecimal("2.3"));
        account3.setName("42");
        account3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book8 = new Book();
        book8.setBalance(new BigDecimal("2.3"));
        book8.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book8.setDefaultAccount(1L);
        book8.setDeleted(true);
        book8.setDescription("com.github.rayinfinite.wallet.model.book.Book");
        book8.setDisabled(true);
        book8.setExpense(new BigDecimal("2.3"));
        book8.setId(3L);
        book8.setIncome(new BigDecimal("2.3"));
        book8.setName("42");
        book8.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book8.setUserId(3L);

        Book book9 = new Book();
        book9.setBalance(new BigDecimal("2.3"));
        book9.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book9.setDefaultAccount(1L);
        book9.setDeleted(true);
        book9.setDescription("com.github.rayinfinite.wallet.model.book.Book");
        book9.setDisabled(true);
        book9.setExpense(new BigDecimal("2.3"));
        book9.setId(3L);
        book9.setIncome(new BigDecimal("2.3"));
        book9.setName("42");
        book9.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book9.setUserId(3L);

        Category category3 = new Category();
        category3.setBook(book9);
        category3.setChild(new ArrayList<>());
        category3.setIcon("42");
        category3.setId(3L);
        category3.setName("42");
        category3.setType(0);

        Transaction transaction3 = new Transaction();
        transaction3.setAccount(account3);
        transaction3.setAmount(new BigDecimal("2.3"));
        transaction3.setBook(book8);
        transaction3.setCategory(category3);
        transaction3.setDeleted(true);
        transaction3.setDetails(new HashMap<>());
        transaction3.setDisabled(true);
        transaction3.setId(3L);
        transaction3.setNotes("42");
        transaction3.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction3.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction3);
        content.add(transaction2);
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(pageImpl);
        List<ChartVO> actualReportExpenseCategoryResult = reportService.reportExpenseCategory();
        assertEquals(1, actualReportExpenseCategoryResult.size());
        ChartVO getResult = actualReportExpenseCategoryResult.get(0);
        assertEquals("42", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory5() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

        Book book4 = getBook();

        Category category2 = new Category();
        category2.setBook(book4);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getCategory()).thenReturn(category2);
        doNothing().when(transaction).setAccount(Mockito.any());
        doNothing().when(transaction).setAmount(Mockito.any());
        doNothing().when(transaction).setBook(Mockito.any());
        doNothing().when(transaction).setCategory(Mockito.any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.any());
        doNothing().when(transaction).setTime(Mockito.any());
        doNothing().when(transaction).setUpdateTime(Mockito.any());
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
        assertTrue(reportService.reportExpenseCategory().isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
        verify(transaction).getCategory();
        verify(transaction).setAccount(Mockito.any());
        verify(transaction).setAmount(Mockito.any());
        verify(transaction).setBook(Mockito.any());
        verify(transaction).setCategory(Mockito.any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.any());
        verify(transaction).setTime(Mockito.any());
        verify(transaction).setUpdateTime(Mockito.any());
    }

    /**
     * Method under test: {@link ReportService#reportExpenseCategory()}
     */
    @Test
    void testReportExpenseCategory6() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(100000);

        Book book4 = getBook();
        Category category2 = mock(Category.class);
        when(category2.getType()).thenReturn(0);
        when(category2.getName()).thenReturn("Name");
        doNothing().when(category2).setBook(Mockito.any());
        doNothing().when(category2).setChild(Mockito.any());
        doNothing().when(category2).setIcon(Mockito.any());
        doNothing().when(category2).setId(anyLong());
        doNothing().when(category2).setName(Mockito.any());
        doNothing().when(category2).setType(anyInt());
        category2.setBook(book4);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(new BigDecimal("2.3"));
        when(transaction.getCategory()).thenReturn(category2);
        doNothing().when(transaction).setAccount(Mockito.any());
        doNothing().when(transaction).setAmount(Mockito.any());
        doNothing().when(transaction).setBook(Mockito.any());
        doNothing().when(transaction).setCategory(Mockito.any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.any());
        doNothing().when(transaction).setTime(Mockito.any());
        doNothing().when(transaction).setUpdateTime(Mockito.any());
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
        List<ChartVO> actualReportExpenseCategoryResult = reportService.reportExpenseCategory();
        assertEquals(1, actualReportExpenseCategoryResult.size());
        ChartVO getResult = actualReportExpenseCategoryResult.get(0);
        assertEquals("Name", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
        verify(transaction, atLeast(1)).getCategory();
        verify(transaction).getAmount();
        verify(transaction).setAccount(Mockito.any());
        verify(transaction).setAmount(Mockito.any());
        verify(transaction).setBook(Mockito.any());
        verify(transaction).setCategory(Mockito.any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.any());
        verify(transaction).setTime(Mockito.any());
        verify(transaction).setUpdateTime(Mockito.any());
        verify(category2).getType();
        verify(category2).getName();
        verify(category2).setBook(Mockito.any());
        verify(category2).setChild(Mockito.any());
        verify(category2).setIcon(Mockito.any());
        verify(category2).setId(anyLong());
        verify(category2).setName(Mockito.any());
        verify(category2).setType(anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportIncomeCategory()}
     */
    @Test
    void testReportIncomeCategory() {
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(new PageImpl<>(new ArrayList<>()));
        assertTrue(reportService.reportIncomeCategory().isEmpty());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportIncomeCategory()}
     */
    @Test
    void testReportIncomeCategory2() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

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
        List<ChartVO> actualReportIncomeCategoryResult = reportService.reportIncomeCategory();
        assertEquals(1, actualReportIncomeCategoryResult.size());
        ChartVO getResult = actualReportIncomeCategoryResult.get(0);
        assertEquals("Name", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportIncomeCategory()}
     */
    @Test
    void testReportIncomeCategory3() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

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

        Book book4 = new Book();
        book4.setBalance(new BigDecimal("2.3"));
        book4.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setDefaultAccount(1L);
        book4.setDeleted(false);
        book4.setDescription("Description");
        book4.setDisabled(false);
        book4.setExpense(new BigDecimal("2.3"));
        book4.setId(2L);
        book4.setIncome(new BigDecimal("2.3"));
        book4.setName("com.github.rayinfinite.wallet.model.book.Book");
        book4.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book4.setUserId(2L);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal("2.3"));
        account2.setBook(book4);
        account2.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        account2.setDeleted(false);
        account2.setDescription("Description");
        account2.setDisabled(false);
        account2.setExpense(new BigDecimal("2.3"));
        account2.setId(2L);
        account2.setIncome(new BigDecimal("2.3"));
        account2.setName("com.github.rayinfinite.wallet.model.account.Account");
        account2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        Book book5 = new Book();
        book5.setBalance(new BigDecimal("2.3"));
        book5.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setDefaultAccount(1L);
        book5.setDeleted(false);
        book5.setDescription("Description");
        book5.setDisabled(false);
        book5.setExpense(new BigDecimal("2.3"));
        book5.setId(2L);
        book5.setIncome(new BigDecimal("2.3"));
        book5.setName("com.github.rayinfinite.wallet.model.book.Book");
        book5.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book5.setUserId(2L);

        Book book6 = new Book();
        book6.setBalance(new BigDecimal("2.3"));
        book6.setCreateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setDefaultAccount(1L);
        book6.setDeleted(false);
        book6.setDescription("Description");
        book6.setDisabled(false);
        book6.setExpense(new BigDecimal("2.3"));
        book6.setId(2L);
        book6.setIncome(new BigDecimal("2.3"));
        book6.setName("com.github.rayinfinite.wallet.model.book.Book");
        book6.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        book6.setUserId(2L);

        Category category2 = new Category();
        category2.setBook(book6);
        category2.setChild(new ArrayList<>());
        category2.setIcon("com.github.rayinfinite.wallet.model.category.Category");
        category2.setId(2L);
        category2.setName("com.github.rayinfinite.wallet.model.category.Category");
        category2.setType(100000);

        Transaction transaction2 = new Transaction();
        transaction2.setAccount(account2);
        transaction2.setAmount(new BigDecimal("2.3"));
        transaction2.setBook(book5);
        transaction2.setCategory(category2);
        transaction2.setDeleted(false);
        transaction2.setDetails(new HashMap<>());
        transaction2.setDisabled(false);
        transaction2.setId(2L);
        transaction2.setNotes("com.github.rayinfinite.wallet.model.transaction.Transaction");
        transaction2.setTime(LocalDate.of(1970, 1, 1).atStartOfDay());
        transaction2.setUpdateTime(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Transaction> content = new ArrayList<>();
        content.add(transaction2);
        content.add(transaction);
        PageImpl<Transaction> pageImpl = new PageImpl<>(content);
        when(transactionService.getPage(anyInt(), anyInt())).thenReturn(pageImpl);
        List<ChartVO> actualReportIncomeCategoryResult = reportService.reportIncomeCategory();
        assertEquals(1, actualReportIncomeCategoryResult.size());
        ChartVO getResult = actualReportIncomeCategoryResult.get(0);
        assertEquals("Name", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
    }

    /**
     * Method under test: {@link ReportService#reportIncomeCategory()}
     */
    @Test
    void testReportIncomeCategory4() {
        Account account = getAccount();

        Book book2 = getBook();

        Book book3 = getBook();

        Category category = new Category();
        category.setBook(book3);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);

        Book book4 = getBook();

        Category category2 = new Category();
        category2.setBook(book4);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(new BigDecimal("2.3"));
        when(transaction.getCategory()).thenReturn(category2);
        doNothing().when(transaction).setAccount(Mockito.any());
        doNothing().when(transaction).setAmount(Mockito.any());
        doNothing().when(transaction).setBook(Mockito.any());
        doNothing().when(transaction).setCategory(Mockito.any());
        doNothing().when(transaction).setDeleted(Mockito.<Boolean>any());
        doNothing().when(transaction).setDetails(Mockito.any());
        doNothing().when(transaction).setDisabled(Mockito.<Boolean>any());
        doNothing().when(transaction).setId(Mockito.<Long>any());
        doNothing().when(transaction).setNotes(Mockito.any());
        doNothing().when(transaction).setTime(Mockito.any());
        doNothing().when(transaction).setUpdateTime(Mockito.any());
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
        List<ChartVO> actualReportIncomeCategoryResult = reportService.reportIncomeCategory();
        assertEquals(1, actualReportIncomeCategoryResult.size());
        ChartVO getResult = actualReportIncomeCategoryResult.get(0);
        assertEquals("Name", getResult.getX());
        BigDecimal expectedY = new BigDecimal("2.3");
        assertEquals(expectedY, getResult.getY());
        verify(transactionService).getPage(anyInt(), anyInt());
        verify(transaction, atLeast(1)).getCategory();
        verify(transaction).getAmount();
        verify(transaction).setAccount(Mockito.any());
        verify(transaction).setAmount(Mockito.any());
        verify(transaction).setBook(Mockito.any());
        verify(transaction).setCategory(Mockito.any());
        verify(transaction).setDeleted(Mockito.<Boolean>any());
        verify(transaction).setDetails(Mockito.any());
        verify(transaction).setDisabled(Mockito.<Boolean>any());
        verify(transaction).setId(Mockito.<Long>any());
        verify(transaction).setNotes(Mockito.any());
        verify(transaction).setTime(Mockito.any());
        verify(transaction).setUpdateTime(Mockito.any());
    }
}

