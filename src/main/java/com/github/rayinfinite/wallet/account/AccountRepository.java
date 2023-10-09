package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.model.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findByName(String keyword, Pageable pageable);
}
