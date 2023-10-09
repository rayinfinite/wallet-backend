package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.dto.AddAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Tag(name = "account")
public class AccountController {
    private final AccountService accountService;

    @Operation(description = "Get account")
    @GetMapping("/")
    public BaseResponse<Account> get(Long id) {
        Account account = accountService.get(id);
        return BaseResponse.success(account);
    }

    @Operation(description = "Add account")
    @PostMapping("/")
    public BaseResponse<String> add(@Validated @RequestBody AddAccount addAccount) {
        accountService.add(addAccount);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Delete account")
    @DeleteMapping("/")
    public BaseResponse<String> delete(Long id) {
        accountService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Update account")
    @PostMapping("/update")
    public BaseResponse<String> update(@Validated @RequestBody Account account) {
        accountService.update(account);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Get account page")
    @GetMapping("/page")
    public BaseResponse<Page<Account>> getPage(String keyword, int page, int size) {
        Page<Account> account= accountService.getPage(keyword, page, size);
        return BaseResponse.success(account);
    }
}
