package com.github.rayinfinite.wallet.category;

import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.category.Category;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CategoryService.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceDiffblueTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CurrentSession currentSession;

    /**
     * Method under test: {@link CategoryService#get(Long)}
     */
    @Test
    void testGet() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertSame(category, categoryService.get(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
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
     * Method under test: {@link CategoryService#get(Long)}
     */
    @Test
    void testGet2() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(currentSession.getBook()).thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> categoryService.get(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#get(Long)}
     */
    @Test
    void testGet3() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(-1L);
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
        Category category = mock(Category.class);
        when(category.getBook()).thenReturn(book2);
        doNothing().when(category).setBook(Mockito.any());
        doNothing().when(category).setChild(Mockito.any());
        doNothing().when(category).setIcon(Mockito.any());
        doNothing().when(category).setId(anyLong());
        doNothing().when(category).setName(Mockito.any());
        doNothing().when(category).setType(anyInt());
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        assertThrows(DefaultException.class, () -> categoryService.get(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(category).getBook();
        verify(category).setBook(Mockito.any());
        verify(category).setChild(Mockito.any());
        verify(category).setIcon(Mockito.any());
        verify(category).setId(anyLong());
        verify(category).setName(Mockito.any());
        verify(category).setType(anyInt());
        verify(book2).getId();
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
        verify(book2).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#get(Long)}
     */
    @Test
    void testGet4() {
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> categoryService.get(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryService#add(long, Category)}
     */
    @Test
    void testAdd() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);

        Book book2 = getBook();

        Category category2 = new Category();
        category2.setBook(book2);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        when(categoryRepository.save(Mockito.any())).thenReturn(category2);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);

        Book book4 = getBook();

        Category category3 = new Category();
        category3.setBook(book4);
        category3.setChild(new ArrayList<>());
        category3.setIcon("Icon");
        category3.setId(1L);
        category3.setName("Name");
        category3.setType(1);
        categoryService.add(1L, category3);
        verify(categoryRepository, atLeast(1)).save(Mockito.any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getBook();
        assertEquals(book2, category3.getBook());
    }

    /**
     * Method under test: {@link CategoryService#add(long, Category)}
     */
    @Test
    void testAdd2() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);

        Book book3 = getBook();

        Category category2 = new Category();
        category2.setBook(book3);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        assertThrows(DefaultException.class, () -> categoryService.add(1L, category2));
        verify(categoryRepository).save(Mockito.any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession, atLeast(1)).getBook();
    }

    /**
     * Method under test: {@link CategoryService#add(long, Category)}
     */
    @Test
    void testAdd3() {
        Book book = getBook();

        Book book2 = getBook();
        Category category = mock(Category.class);
        when(category.getChild()).thenThrow(new DefaultException("An error occurred"));
        when(category.getBook()).thenReturn(book2);
        doNothing().when(category).setBook(Mockito.any());
        doNothing().when(category).setChild(Mockito.any());
        doNothing().when(category).setIcon(Mockito.any());
        doNothing().when(category).setId(anyLong());
        doNothing().when(category).setName(Mockito.any());
        doNothing().when(category).setType(anyInt());
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);

        Book book4 = getBook();

        Category category2 = new Category();
        category2.setBook(book4);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        assertThrows(DefaultException.class, () -> categoryService.add(1L, category2));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(category).getBook();
        verify(category).getChild();
        verify(category).setBook(Mockito.any());
        verify(category).setChild(Mockito.any());
        verify(category).setIcon(Mockito.any());
        verify(category).setId(anyLong());
        verify(category).setName(Mockito.any());
        verify(category).setType(anyInt());
        verify(currentSession, atLeast(1)).getBook();
    }

    /**
     * Method under test: {@link CategoryService#delete(Long)}
     */
    @Test
    void testDelete() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepository).delete(Mockito.any());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        categoryService.delete(1L);
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).delete(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#delete(Long)}
     */
    @Test
    void testDelete2() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new DefaultException("An error occurred")).when(categoryRepository).delete(Mockito.any());
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        assertThrows(DefaultException.class, () -> categoryService.delete(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(categoryRepository).delete(Mockito.any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#delete(Long)}
     */
    @Test
    void testDelete3() {
        Book book = getBook();
        Book book2 = mock(Book.class);
        when(book2.getId()).thenReturn(-1L);
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
        Category category = mock(Category.class);
        when(category.getBook()).thenReturn(book2);
        doNothing().when(category).setBook(Mockito.any());
        doNothing().when(category).setChild(Mockito.any());
        doNothing().when(category).setIcon(Mockito.any());
        doNothing().when(category).setId(anyLong());
        doNothing().when(category).setName(Mockito.any());
        doNothing().when(category).setType(anyInt());
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);
        assertThrows(DefaultException.class, () -> categoryService.delete(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(category).getBook();
        verify(category).setBook(Mockito.any());
        verify(category).setChild(Mockito.any());
        verify(category).setIcon(Mockito.any());
        verify(category).setId(anyLong());
        verify(category).setName(Mockito.any());
        verify(category).setType(anyInt());
        verify(book2).getId();
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
        verify(book2).setDefaultAccount(anyLong());
        verify(book2).setUserId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#delete(Long)}
     */
    @Test
    void testDelete4() {
        Optional<Category> emptyResult = Optional.empty();
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(DefaultException.class, () -> categoryService.delete(1L));
        verify(categoryRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CategoryService#update(Long, Category)}
     */
    @Test
    void testUpdate() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);

        Book book2 = getBook();

        Category category2 = new Category();
        category2.setBook(book2);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        when(categoryRepository.save(Mockito.any())).thenReturn(category2);
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book3 = getBook();
        when(currentSession.getBook()).thenReturn(book3);

        Book book4 = getBook();

        Category category3 = new Category();
        category3.setBook(book4);
        category3.setChild(new ArrayList<>());
        category3.setIcon("Icon");
        category3.setId(1L);
        category3.setName("Name");
        category3.setType(1);
        categoryService.update(1L, category3);
        verify(categoryRepository).save(Mockito.any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#update(Long, Category)}
     */
    @Test
    void testUpdate2() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepository.save(Mockito.any())).thenThrow(new DefaultException("An error occurred"));
        when(categoryRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);

        Book book3 = getBook();

        Category category2 = new Category();
        category2.setBook(book3);
        category2.setChild(new ArrayList<>());
        category2.setIcon("Icon");
        category2.setId(1L);
        category2.setName("Name");
        category2.setType(1);
        assertThrows(DefaultException.class, () -> categoryService.update(1L, category2));
        verify(categoryRepository).save(Mockito.any());
        verify(categoryRepository).findById(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#list()}
     */
    @Test
    void testList() {
        ArrayList<Category> categoryList = new ArrayList<>();
        when(categoryRepository.findByBookId(Mockito.<Long>any())).thenReturn(categoryList);

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        List<Category> actualListResult = categoryService.list();
        assertSame(categoryList, actualListResult);
        assertTrue(actualListResult.isEmpty());
        verify(categoryRepository).findByBookId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#list()}
     */
    @Test
    void testList2() {
        when(categoryRepository.findByBookId(Mockito.<Long>any())).thenThrow(new DefaultException("An error occurred"));

        Book book = getBook();
        when(currentSession.getBook()).thenReturn(book);
        assertThrows(DefaultException.class, () -> categoryService.list());
        verify(categoryRepository).findByBookId(Mockito.<Long>any());
        verify(currentSession).getBook();
    }

    /**
     * Method under test: {@link CategoryService#init()}
     */
    @Test
    void testInit() {
        Book book = getBook();

        Category category = new Category();
        category.setBook(book);
        category.setChild(new ArrayList<>());
        category.setIcon("Icon");
        category.setId(1L);
        category.setName("Name");
        category.setType(1);
        when(categoryRepository.save(Mockito.any())).thenReturn(category);

        Book book2 = getBook();
        when(currentSession.getBook()).thenReturn(book2);
        categoryService.init();
        verify(categoryRepository, atLeast(1)).save(Mockito.any());
        verify(currentSession, atLeast(1)).getBook();
    }

    /**
     * Method under test: {@link CategoryService#init()}
     */
    @Test
    void testInit2() {
        when(currentSession.getBook()).thenThrow(new DefaultException("An error occurred"));
        assertThrows(DefaultException.class, () -> categoryService.init());
        verify(currentSession).getBook();
    }
}

