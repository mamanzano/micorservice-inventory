package com.inventory.product.persistence.crudrepository;

import com.inventory.product.persistence.entity.Producto;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductCrudRepository extends ListCrudRepository<Producto,Long> {
}
