package com.inventory.category.web.controller;

import com.inventory.category.domain.service.CategoryService;
import com.inventory.category.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Category> categories = service.getAll();
        if (!categories.isEmpty()){
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
    }

    @GetMapping("/byd/{id}")
    public ResponseEntity<?> byId(@PathVariable(name = "id") Long id){
        return service.getCategoryById(id).map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseThrow();
    }

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Category category){
        return service.save(category).map(category1 -> new ResponseEntity<>(category1, HttpStatus.OK))
                .orElseThrow();

    }

    @PatchMapping("/udapte")
    public ResponseEntity<?> byId(@RequestBody Category category){
        return service.save(category).map(category1 -> new ResponseEntity<>(category1, HttpStatus.OK))
                .orElseThrow();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletebyId(@PathVariable(name = "id") Long id){
        if (service.delete(id)){

            return service.getCategoryById(id).map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElseThrow();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
