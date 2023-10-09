package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.model.transaction.dto.AddTransaction;
import com.github.rayinfinite.wallet.model.transaction.dto.UpdateTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
@Tag(name = "transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(description = "Get transaction")
    @GetMapping("/")
    public BaseResponse<Transaction> get(Long id) {
        Transaction transaction = transactionService.get(id);
        return BaseResponse.success(transaction);
    }

    @Operation(description = "Add transaction")
    @PostMapping("/")
    public BaseResponse<String> add(AddTransaction addTransaction) {
        transactionService.add(addTransaction);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Update transaction")
    @PostMapping("/update")
    public BaseResponse<String> update(UpdateTransaction updateTransaction) {
        transactionService.update(updateTransaction);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Delete transaction")
    @DeleteMapping("/")
    public BaseResponse<String> delete(Long id) {
        transactionService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Get transaction page")
    @GetMapping("/page")
    public BaseResponse<Page<Transaction>> getPage(int page, int size) {
        Page<Transaction> transaction = transactionService.getPage(page, size);
        return BaseResponse.success(transaction);
    }

    @Operation(description = "Get transaction page by timestamp range")
    @GetMapping("/range")
    public BaseResponse<Page<Transaction>> getRangePage(int page, int size, long start, long end) {
        Page<Transaction> transaction = transactionService.getPage(page, size, start, end);
        return BaseResponse.success(transaction);
    }

}
