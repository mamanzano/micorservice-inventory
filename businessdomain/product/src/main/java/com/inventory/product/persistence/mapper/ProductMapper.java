package com.inventory.product.persistence.mapper;

import com.inventory.product.domain.dto.Product;
import com.inventory.product.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "categoria", target = "category")
    })
    Product toProduct(Producto producto);

    @InheritInverseConfiguration
    Producto toProducto(Product product);

    List<Product> toProducts(List<Producto> productos);

}
