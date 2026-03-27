package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoGetByIdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoGetByIdService implements IProductoGetByIdService {
    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto getById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
        return ProductoMapper.toResponseDto(producto);
    }
}
