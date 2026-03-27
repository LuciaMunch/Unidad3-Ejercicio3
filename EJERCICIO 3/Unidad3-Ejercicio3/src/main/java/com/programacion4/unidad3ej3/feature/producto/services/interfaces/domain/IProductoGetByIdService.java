package com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;

public interface IProductoGetByIdService {
    ProductoResponseDto getById(Long id);
}
