package com.inventory.product.web.controller;


import com.inventory.product.domain.dto.ApiResponse;
import com.inventory.product.domain.dto.Product;
import com.inventory.product.domain.service.ProductService;
import com.inventory.product.domain.service.exception.ResourceNotFoundException;
import com.inventory.product.persistence.entity.Producto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/products")
    public ResponseEntity<?> getAll(){
        List<Product> products = service.getAll();
        if (!products.isEmpty()){
            return new ResponseEntity<>(new ApiResponse<List<Product>>(HttpStatus.OK.value(),"Productos", products),HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No existen productos", null));
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Long id){
        return service.getProduct(id).map(product -> new
                        ResponseEntity<>(new ApiResponse<Product>(HttpStatus.OK.value(),"Producto",product), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Product product){
        LOGGER.info("Creating product: {}", product.toString());
        return service.addProduct(product).map(product1 -> {
            ApiResponse apiResponse = new ApiResponse(HttpStatus.CREATED.value(), "Producto creado", product1);
            LOGGER.info("Response: {}", apiResponse);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }).orElseThrow(() -> {
            ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("No fue posible crear el producto");
            LOGGER.info("Response: {}: ", resourceNotFoundException);
            return resourceNotFoundException;
        });

    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Product product){
        return service.updateProduct(product).map(product1 ->
                        new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(),"Producto actualizado",product1), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("No fue posible actualizar el producto"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        if (service.delete(id)){
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), "Producto eliminado", null),  HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResourceNotFoundException("No fue posible borrar el producto"), HttpStatus.NOT_FOUND    );
    }


}
