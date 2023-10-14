package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.exception.DefaultException;
import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CurrentSession currentSession;

    public Account get(Long id) {
        Account account= accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new DefaultException("账户不存在");
        }
        if (!account.getBook().getId().equals(currentSession.getBook().getId())) {
            throw new DefaultException("没有访问权限");
        }
        return account;
    }

    public Account add(AddAccount addAccount) {
        Account account = new Account();
        BeanUtils.copyProperties(addAccount, account);
        account.setBook(currentSession.getBook());
        return accountRepository.save(account);
    }

    public void update(long id, AddAccount addAccount) {
        Account account = get(id);
        BeanUtils.copyProperties(addAccount, account);
        account.setUpdateTime(LocalDateTime.now());
        accountRepository.save(account);
    }

    public void delete(Long id) {
        get(id);
        accountRepository.deleteById(id);
    }

    public Page<Account> getPage(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if (keyword == null || keyword.isEmpty()) {
            return accountRepository.findByBookId(currentSession.getBook().getId(), pageable);
        }
        return accountRepository.findByNameContainingAndBookId(keyword, currentSession.getBook().getId(), pageable);
    }
}
