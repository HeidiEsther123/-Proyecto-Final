package mx.tecdesoftware.streaming_backend.web.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura cuando alguien manda un ID no numérico (ej. /api/series/abc)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String expectedType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "valid value";
        return ResponseEntity.badRequest()
                .body(Map.of("error", "Invalid value for '" + ex.getName() + "': expected a " + expectedType));
    }

    // Captura violaciones de integridad referencial (ej. id_serie que no existe)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", "The request violates a database constraint. Check that referenced IDs exist and required fields are present."));
    }
}
