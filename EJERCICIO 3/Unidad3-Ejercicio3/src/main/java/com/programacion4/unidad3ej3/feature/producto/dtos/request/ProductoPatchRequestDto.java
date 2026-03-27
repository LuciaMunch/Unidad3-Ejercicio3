package com.programacion4.unidad3ej3.feature.producto.dtos.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoPatchRequestDto {
    private Double precio;
    private Integer stock;
}
