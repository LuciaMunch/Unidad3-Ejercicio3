package com.programacion4.unidad3ej3.config;

import com.programacion4.unidad3ej3.config.exceptions.ConflictException;
import com.programacion4.unidad3ej3.config.exceptions.CustomException;
import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<BaseResponse<Object>> handleConflict(
            ConflictException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildResponse(ex, request.getRequestURI()), ex.getStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildResponse(ex, request.getRequestURI()), ex.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Object>> handleCustomException(
            CustomException ex, HttpServletRequest request) {
        return new ResponseEntity<>(buildResponse(ex, request.getRequestURI()), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();

        BaseResponse<Object> response = BaseResponse.builder()
                .message("Error de validación")
                .errors(errors)
                .timestamp(Instant.now().toString())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneric(
            Exception ex, HttpServletRequest request) {
        BaseResponse<Object> response = BaseResponse.builder()
                .message("Ocurrió un error inesperado")
                .errors(List.of("Contacte al administrador"))
                .timestamp(Instant.now().toString())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.internalServerError().body(response);
    }

    private BaseResponse<Object> buildResponse(CustomException ex, String path) {
        return BaseResponse.builder()
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .timestamp(Instant.now().toString())
                .path(path)
                .build();
    }
}
