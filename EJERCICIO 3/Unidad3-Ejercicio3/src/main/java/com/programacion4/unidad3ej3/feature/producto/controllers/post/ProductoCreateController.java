package com.programacion4.unidad3ej3.feature.producto.controllers.post;

import com.programacion4.unidad3ej3.config.BaseResponse;
import com.programacion4.unidad3ej3.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej3.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoCreateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoCreateController {

    private final IProductoCreateService productoCreateService;

    @PostMapping
    public ResponseEntity<BaseResponse<ProductoResponseDto>> create(
            @Valid @RequestBody ProductoCreateRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.ok(productoCreateService.create(dto), "Producto creado correctamente")
        );
    }
}
