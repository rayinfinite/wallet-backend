package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.ChartVO;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.book.AddBook;
import com.github.rayinfinite.wallet.model.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@Tag(name = "book")
public class BookController {
    private final BookService bookService;
    private final ReportService reportService;

    @Operation(description = "Get book", operationId = "getBook")
    @GetMapping("/{id}")
    public BaseResponse<Book> get(@PathVariable("id") long id) {
        Book book = bookService.get(id);
        return BaseResponse.success(book);
    }

    @Operation(description = "Add book", operationId = "addBook")
    @PostMapping("/")
    public BaseResponse<String> add(AddBook addBook) {
        bookService.add(addBook);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Delete book", operationId = "deleteBook")
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Update book", operationId = "updateBook")
    @PostMapping("/{id}")
    public BaseResponse<String> update(@PathVariable("id") long id, AddBook addBook) {
        bookService.update(id, addBook);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Get book list", operationId = "getBookList")
    @PostMapping("/list")
    public BaseResponse<List<Book>> list() {
        return BaseResponse.success(bookService.list());
    }

    @Operation(description = "Get user list", operationId = "getUserList")
    @PostMapping("/user")
    public BaseResponse<List<User>> getUser(Book book) {
        return BaseResponse.success(bookService.getUser(book));
    }

    @Operation(description = "Book Overview")
    @GetMapping("/overview")
    public BaseResponse<List<BigDecimal>> overview(){
        return BaseResponse.success(reportService.overview());
    }

    @Operation(description = "Report Balance")
    @GetMapping("/reportBalance")
    public BaseResponse<List<List<ChartVO>>> reportBalance(){
        return BaseResponse.success(reportService.reportBalance());
    }

    @Operation(description = "Report Expense Category")
    @GetMapping("/reportExpenseCategory")
    public BaseResponse<List<ChartVO>> reportExpenseCategory(){
        return BaseResponse.success(reportService.reportExpenseCategory());
    }

    @Operation(description = "Report Income Category")
    @GetMapping("/reportIncomeCategory")
    public BaseResponse<List<ChartVO>> reportIncomeCategory(){
        return BaseResponse.success(reportService.reportIncomeCategory());
    }
}
