package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import com.programacion4.unidad3ej3.config.exceptions.ConflictException;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.models.Producto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.commons.IProductoExistByNameService;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoCreateService implements IProductoCreateService {

    private final IProductoExistByNameService productoExistByNameService;
    private final IProductoRepository productoRepository;

    @Override
    public ProductoResponseDto create(ProductoCreateRequestDto dto) {

        String nombreCapitalizado = capitalize(dto.getNombre());

        if (productoExistByNameService.existByName(nombreCapitalizado)) {
            throw new ConflictException("Ya existe un producto con el nombre: " + nombreCapitalizado);
        }

        Producto productoAGuardar = ProductoMapper.toEntity(dto);
        productoAGuardar.setNombre(nombreCapitalizado);
        productoAGuardar.setDescripcion(capitalize(dto.getDescripcion()));
        productoAGuardar.setEstaEliminado(false);

        return ProductoMapper.toResponseDto(productoRepository.save(productoAGuardar));
    }

    private String capitalize(String texto) {
        if (texto == null || texto.isBlank()) return texto;
        String lower = texto.trim().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
