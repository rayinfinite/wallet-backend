package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByNameContainingAndBookId(String name, Long bookId, Pageable pageable);
    Page<Account> findByBookId(Long bookId, Pageable pageable);
    List<Account> findByBookId(Long bookId);
}
