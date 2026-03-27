package com.programacion4.unidad3ej3.config.exceptions;

import org.springframework.http.HttpStatus;
import java.util.List;

public class ConflictException extends CustomException {
    public ConflictException(String message) {

        super(message,HttpStatus.CONFLICT,List.of(message));
    }
}
