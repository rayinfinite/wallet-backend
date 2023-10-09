package com.github.rayinfinite.wallet.UserBookRelation;

import com.github.rayinfinite.wallet.model.UserBookRelation;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBookRelationService {
    private final UserBookRelationRepository userBookRelationRepository;
    public void deleteByBook(Book book) {
        userBookRelationRepository.deleteByBook(book);
    }

    public void deleteByUser(User user) {
        userBookRelationRepository.deleteByUser(user);
    }

    public UserBookRelation add(User user, Book book, int role) {
        UserBookRelation userBookRelation=new UserBookRelation();
        userBookRelation.setBook(book);
        userBookRelation.setUser(user);
        userBookRelation.setRole(role);
        return userBookRelationRepository.save(userBookRelation);
    }

    public List<UserBookRelation> findByUser(User user) {
        return userBookRelationRepository.findByUser(user);
    }

    public List<UserBookRelation> findByBook(Book book) {
        return userBookRelationRepository.findByBook(book);
    }

    public List<UserBookRelation> findByUserAndRole(User user, Integer role) {
        return userBookRelationRepository.findByUserAndRole(user, role);
    }

    public UserBookRelation findByUserAndBook(User user, Book book) {
        return userBookRelationRepository.findByUserAndBook(user, book).orElse(null);
    }
}
