package com.github.rayinfinite.wallet.category;

import com.github.rayinfinite.wallet.model.BaseResponse;
import com.github.rayinfinite.wallet.model.category.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
@Tag(name = "category")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(description = "Get category", operationId = "getCategory")
    @GetMapping("/{id}")
    public BaseResponse<Category> get(@PathVariable("id") long id) {
        Category category = categoryService.get(id);
        return BaseResponse.success(category);
    }

    @Operation(description = "Add category", operationId = "addCategory")
    @PostMapping("/add/{id}")
    public BaseResponse<String> add(@PathVariable("id") long id, @Validated @RequestBody Category category) {
        categoryService.add(id, category);
        return BaseResponse.success("Add successfully");
    }

    @Operation(description = "Delete category", operationId = "deleteCategory")
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") long id) {
        categoryService.delete(id);
        return BaseResponse.success("Delete successfully");
    }

    @Operation(description = "Update category", operationId = "updateCategory")
    @PostMapping("/{id}")
    public BaseResponse<String> update(@PathVariable("id") long id, @Validated @RequestBody Category category) {
        categoryService.update(id, category);
        return BaseResponse.success("Update successfully");
    }

    @Operation(description = "Get category page", operationId = "getCategoryPage")
    @GetMapping("/list")
    public BaseResponse<List<Category>> list() {
        return BaseResponse.success(categoryService.list());
    }

    @Operation(description = "Initialize category", operationId = "initCategory")
    @GetMapping("/init")
    public BaseResponse<String> init() {
        categoryService.init();
        return BaseResponse.success("Initialize successfully");
    }
}
