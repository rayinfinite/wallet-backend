package com.github.rayinfinite.wallet.category;

import com.github.rayinfinite.wallet.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByBookId(Long bookId);
}
