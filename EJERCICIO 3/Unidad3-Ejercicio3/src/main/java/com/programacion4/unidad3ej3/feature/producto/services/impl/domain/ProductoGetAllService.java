package com.programacion4.unidad3ej3.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej3.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoGetAllService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.StreamSupport;


@Service
@AllArgsConstructor
public class ProductoGetAllService implements IProductoGetAllService {
    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> getAll() {
        return StreamSupport
                .stream(productoRepository.findAll().spliterator(), false)
                .map(ProductoMapper::toResponseDto)
                .toList();
    }
}
