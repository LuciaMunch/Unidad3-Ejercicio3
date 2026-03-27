package com.programacion4.unidad3ej3.feature.producto.controllers.delete;

import com.programacion4.unidad3ej3.feature.producto.services.interfaces.domain.IProductoDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoDeleteController {

    private final IProductoDeleteService productoDeleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
