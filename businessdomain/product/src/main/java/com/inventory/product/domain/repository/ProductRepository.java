package com.inventory.product.domain.repository;

import com.inventory.product.domain.dto.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository {
    List<Product> getAll();
    Optional<Product> getProduct(Long id);
    Optional<Product>  addProduct(Product product);
    Optional<Product>  updateProduct(Product product);
    Boolean delete(Long id);
    Boolean exist(Long id);
}
