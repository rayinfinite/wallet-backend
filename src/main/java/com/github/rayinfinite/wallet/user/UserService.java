package com.github.rayinfinite.wallet.user;

import com.github.rayinfinite.wallet.account.AccountService;
import com.github.rayinfinite.wallet.book.BookService;
import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.user.User;
import com.github.rayinfinite.wallet.model.user.dto.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookService bookService;
    private final AccountService accountService;
    private final PasswordEncoder PasswordEncoder;
    private final CurrentSession currentSession;

    public User login(@NotNull Login login) {
        User user = userRepository.findOneByUsername(login.getUsername()).orElse(null);
        if (user == null) {
            throw new DefaultException("User not found");
        }
        if (!PasswordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new DefaultException("User login wrong Password");
        }
        currentSession.setUser(user);
        currentSession.setBook(bookService.get(user.getDefaultBook()));
        System.out.println(currentSession.getBook());
        return user;
    }

    /**
     * 注册
     * 如果存在相同的用户名，返回null。创建user并存入数据库，搜索并返回user。
     */
    @Transactional
    public User register(@NotNull AddUser addUser) {
        if (userRepository.findOneByUsername(addUser.getUsername()).isPresent()) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(addUser, user);
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        User saved=userRepository.save(user);
        currentSession.setUser(saved);

        Book book=bookService.add(new AddBook("default", "Default Book"));
        user.setDefaultBook(book.getId());
        currentSession.setBook(book);

        Account account=accountService.add(new AddAccount("Cash", "Default Account"));
        book = bookService.setDefaultAccount(account.getId());

        return setDefaultBook(book.getId());
    }

    public void changePassword(@NotNull ChangePassword changePassword) {
        User user= currentSession.getUser();
        if (!PasswordEncoder.matches(changePassword.getOldPassword(), user.getPassword())) {
            throw new DefaultException("incorrect previous password");
        }
        user.setPassword(PasswordEncoder.encode(changePassword.getNewPassword()));
        userRepository.save(user);
    }

    public void forgetPassword(@NotNull ForgetPassword forgetPassword) {
        User user = userRepository.findOneByUsername(forgetPassword.getUsername()).orElse(null);
        if (user == null) {
            throw new DefaultException("User not found");
        }
        if (!forgetPassword.validate(user)) {
            throw new DefaultException("User information incorrect");
        }
        user.setPassword(PasswordEncoder.encode(forgetPassword.getNewPassword()));
        userRepository.save(user);
    }

    public User CurrentUser() {
        return currentSession.getUser();
    }

    public void update(UpdateUser updateUser) {
        User user = currentSession.getUser();
        BeanUtils.copyProperties(updateUser, user);
        userRepository.save(user);
    }

    public User get(long id){
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            throw new DefaultException("User not found");
        }
        currentSession.setUser(user);
        currentSession.setBook(bookService.get(user.getDefaultBook()));
        return user;
    }

    public User setDefaultBook(long defaultBook){
        User user=currentSession.getUser();
        user.setDefaultBook(defaultBook);
        return userRepository.save(user);
    }
}
