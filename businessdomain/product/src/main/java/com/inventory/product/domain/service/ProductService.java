package com.inventory.product.domain.service;

import com.inventory.product.domain.dto.Product;
import com.inventory.product.domain.repository.ProductRepository;
import com.inventory.product.persistence.crudrepository.ProductCrudRepository;
import com.inventory.product.persistence.entity.Producto;
import com.inventory.product.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        return mapper.toProducts(productCrudRepository.findAll());
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        Optional<Producto> oProdcto = productCrudRepository.findById(id);
        return Optional.of(mapper.toProduct(oProdcto.get()));
    }

    @Override
    public Optional<Product>  addProduct(Product product) {
        Producto producto = mapper.toProducto(product);
        return Optional.of(mapper.toProduct(productCrudRepository.save(producto)));
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        Producto producto = mapper.toProducto(product);
        if (exist(producto.getIdProducto())){
            return Optional.of(mapper.toProduct(productCrudRepository.save(producto)));
        }

        return Optional.empty();

    }

    @Override
    public Boolean  delete(Long id) {
        if (exist(id)){
            productCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean exist(Long id) {
        return productCrudRepository.existsById(id);
    }
}
