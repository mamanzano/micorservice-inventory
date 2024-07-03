package com.inventory.product.persistence.mapper;

import com.inventory.product.domain.dto.Category;
import com.inventory.product.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "nombre", target = "name"),
    })
    Category toCategory(Categoria categoria);


    @InheritInverseConfiguration
    Categoria toCategoria(Category category);

    List<Category> toCategories(List<Categoria> categorias);

}
