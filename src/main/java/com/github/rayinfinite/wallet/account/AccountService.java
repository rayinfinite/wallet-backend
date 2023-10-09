package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.model.CurrentSession;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.dto.AddAccount;
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
        return accountRepository.findById(id).orElse(null);
    }

    public Account add(AddAccount addAccount) {
        Account account = new Account();
        BeanUtils.copyProperties(addAccount, account);
        account.setBook(currentSession.getBook());
        return accountRepository.save(account);
    }

    public void update(Account account) {
        //TODO: auth
        account.setUpdateTime(LocalDateTime.now());
        accountRepository.save(account);
    }

    public void delete(Long id) {
        //TODO: auth
        accountRepository.deleteById(id);
    }

    public Page<Account> getPage(String keyword, int page, int size) {
        //TODO: auth
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findByName(keyword, pageable);
    }
}
