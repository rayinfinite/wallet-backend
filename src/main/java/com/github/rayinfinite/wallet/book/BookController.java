package com.github.rayinfinite.wallet.book;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.book.Book;
import com.github.rayinfinite.wallet.model.book.dto.AddBook;
import com.github.rayinfinite.wallet.model.book.dto.UpdateBook;
import com.github.rayinfinite.wallet.model.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
@Tag(name = "book")
public class BookController {
    private final BookService bookService;

    @Operation(description = "Get book")
    @GetMapping("/")
    public BaseResponse<Book> get(Long id) {
        Book book = bookService.get(id);
        return BaseResponse.success(book);
    }

    @Operation(description = "Add book")
    @PostMapping("/")
    public BaseResponse<String> add(AddBook addBook) {
        bookService.add(addBook);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Delete book")
    @DeleteMapping("/")
    public BaseResponse<String> delete(Long id) {
        bookService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Update book")
    @PostMapping("/update")
    public BaseResponse<String> update(UpdateBook updateBook) {
        bookService.update(updateBook);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Get book list")
    @PostMapping("/list")
    public BaseResponse<List<Book>> list(User user) {
        return BaseResponse.success(bookService.list(user));
    }

    @Operation(description = "Get user list")
    @PostMapping("/user")
    public BaseResponse<List<User>> getUser(Book book) {
        return BaseResponse.success(bookService.getUser(book));
    }
}
