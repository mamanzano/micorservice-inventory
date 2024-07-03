package com.inventory.category.persistence.crudrepository;

import com.inventory.category.persistence.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryCrudRepository extends ListCrudRepository<Category,Long> {
}
