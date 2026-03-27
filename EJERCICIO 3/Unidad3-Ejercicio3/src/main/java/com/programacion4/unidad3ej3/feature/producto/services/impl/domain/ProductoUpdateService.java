package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoUpdateService implements IProductoUpdateService {
    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto update(Long id, ProductoUpdateRequestDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        producto.setNombre(capitalize(dto.getNombre()));
        producto.setDescripcion(capitalize(dto.getDescripcion()));
        producto.setCodigo(dto.getCodigo());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        return ProductoMapper.toResponseDto(productoRepository.save(producto));
    }

    private String capitalize(String texto) {
        if (texto == null || texto.isBlank()) return texto;
        String lower = texto.trim().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
