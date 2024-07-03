package com.inventory.category.domain.service;

import com.inventory.category.domain.repository.CategoryRepository;
import com.inventory.category.persistence.crudrepository.CategoryCrudRepository;
import com.inventory.category.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryRepository {

    @Autowired
    CategoryCrudRepository repository;
    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return (repository.findById(id));
    }

    @Override
    public Optional<Category> save(Category category) {
        return Optional.of(repository.save(category));
    }

    @Override
    public Optional<Category> update(Category category) {
        if (exists(category.getId())){
            return Optional.of(repository.save(category));
        }

        return Optional.empty();
    }

    @Override
    public Boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean delete(Long id) {
        if (exists(id)){
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
