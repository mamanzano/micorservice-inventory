package com.inventory.product.persistence.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Categoria {
    Long idCategoria;
    String nombre;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long id) {
        this.idCategoria = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
