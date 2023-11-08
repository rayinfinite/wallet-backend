package com.github.rayinfinite.wallet.transaction;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.transaction.Transaction;
import com.github.rayinfinite.wallet.model.transaction.AddTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transaction")
@Tag(name = "transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(description = "Get transaction", operationId = "getTransaction")
    @GetMapping("/{id}")
    public BaseResponse<Transaction> get(@PathVariable("id") long id) {
        Transaction transaction = transactionService.get(id);
        return BaseResponse.success(transaction);
    }

    @Operation(description = "Add transaction", operationId = "addTransaction")
    @PostMapping("/")
    public BaseResponse<String> add(AddTransaction addTransaction) {
        transactionService.add(addTransaction);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Update transaction", operationId = "updateTransaction")
    @PostMapping("/{id}")
    public BaseResponse<String> update(@PathVariable("id") long id, AddTransaction addTransaction) {
        transactionService.update(id, addTransaction);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Delete transaction", operationId = "deleteTransaction")
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") long id) {
        transactionService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Get transaction page", operationId = "getTransactionPage")
    @GetMapping("/page")
    public BaseResponse<Page<Transaction>> getPage(int current, int pageSize) {
        Page<Transaction> transaction = transactionService.getPage(current, pageSize);
        return BaseResponse.success(transaction);
    }

    @Operation(description = "Get transaction page by timestamp range", operationId = "getTransactionRange")
    @GetMapping("/range")
    public BaseResponse<Page<Transaction>> getRangePage(int current, int pageSize,
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") LocalDateTime start,
                                                        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") LocalDateTime end) {
        // 在这里，start 和 end 参数将会被自动转换为 LocalDateTime 对象
        Page<Transaction> transaction = transactionService.getPage(current, pageSize, start, end);
        return BaseResponse.success(transaction);
    }

}
