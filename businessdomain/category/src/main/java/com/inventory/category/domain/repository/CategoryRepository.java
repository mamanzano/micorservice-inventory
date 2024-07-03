package com.inventory.category.domain.repository;

import com.inventory.category.persistence.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CategoryRepository {
    List<Category> getAll();
    Optional<Category> getCategoryById(Long id);
    Optional<Category> save(Category category);
    Optional<Category> update(Category category);
    Boolean exists(Long id);
    Boolean delete(Long id);
}
