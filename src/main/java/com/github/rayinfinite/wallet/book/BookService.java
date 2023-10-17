package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.UserBookRelation.UserBookRelationService;
import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.category.CategoryService;
import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.UserBookRelation;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CurrentSession currentSession;
    private final UserBookRelationService userBookRelationService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @Transactional
    public Book add(AddBook addBook) {
        Book book = new Book();
        BeanUtils.copyProperties(addBook, book);
        book.setUserId(currentSession.getUser().getId());
        Book saved = bookRepository.save(book);
        userBookRelationService.add(currentSession.getUser(), saved, 0);
        currentSession.setBook(saved);
        Account account = accountService.add(new AddAccount("Cash", "Default Account"));
        categoryService.init();
        return setDefaultAccount(account.getId());
    }

    @Transactional
    public void delete(Long id) {
        Book book = get(id, new int[]{0, 1});
        userBookRelationService.deleteByBook(book);
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(long id, AddBook addBook) {
        Book book = get(id, new int[]{0, 1, 2});
        BeanUtils.copyProperties(addBook, book);
        bookRepository.save(book);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public Book get(Long id) {
        return get(id, new int[]{0, 1, 2, 3});
    }

    public List<Book> list() {
        User user = currentSession.getUser();
        return userBookRelationService.findByUser(user).stream().map(UserBookRelation::getBook).toList();
    }

    public List<User> getUser(Book book) {
        return userBookRelationService.findByBook(book).stream().map(UserBookRelation::getUser).toList();
    }

    public Book get(Long id, int[] roles) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            throw new DefaultException("账本不存在");
        }
        UserBookRelation userBookRelation = userBookRelationService.findByUserAndBook(currentSession.getUser(), book);
        if (userBookRelation == null) {
            throw new DefaultException("没有访问权限");
        }
        for (int role : roles) {
            if (userBookRelation.getRole() == role) {
                return book;
            }
        }
        throw new DefaultException("没有操作权限");
    }

    public Book setDefaultAccount(long defaultAccountId) {
        Book book = currentSession.getBook();
        book.setDefaultAccount(defaultAccountId);
        return bookRepository.save(book);
    }
}
