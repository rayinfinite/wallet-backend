package com.github.rayinfinite.wallet.UserBookRelation;

import com.github.rayinfinite.wallet.model.UserBookRelation;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRelationRepository extends JpaRepository<UserBookRelation, Long> {

    Optional<UserBookRelation> findByUserAndBook(User user, Book book);

    void deleteByBook(Book book);

    void deleteByUser(User user);

    List<UserBookRelation> findByUserAndRole(User user, Integer role);

    List<UserBookRelation> findByBook(Book book);

    List<UserBookRelation> findByUser(User user);
}
