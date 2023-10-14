package com.github.rayinfinite.wallet.account;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.account.Account;
import com.github.rayinfinite.wallet.model.account.AddAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Tag(name = "Account")
public class AccountController {
    private final AccountService accountService;

    @Operation(description = "Get account", operationId = "getAccount")
    @GetMapping("/{id}")
    public BaseResponse<Account> get(@PathVariable("id") long id) {
        Account account = accountService.get(id);
        return BaseResponse.success(account);
    }

    @Operation(description = "Add account", operationId = "addAccount")
    @PostMapping("/")
    public BaseResponse<String> add(@Validated @RequestBody AddAccount addAccount) {
        accountService.add(addAccount);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Delete account", operationId = "deleteAccount")
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") long id) {
        accountService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Update account", operationId = "updateAccount")
    @PostMapping("/{id}")
    public BaseResponse<String> update(@PathVariable("id") long id,@Validated @RequestBody AddAccount addAccount) {
        accountService.update(id,addAccount);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Get account page", operationId = "getAccountPage")
    @GetMapping("/page")
    public BaseResponse<Page<Account>> getPage(String name, int current, int pageSize) {
        Page<Account> account= accountService.getPage(name, current, pageSize);
        return BaseResponse.success(account);
    }
}
