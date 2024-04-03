package br.com.fullstack.education.m1s12.exception;

import br.com.fullstack.education.m1s12.exception.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception e) {
        log.error("STATUS: 500 -> Falha inesperada -> {}, {}", e.getMessage(), e.getCause().getStackTrace());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handler(NotFoundException e) {
        log.error("STATUS: 404 -> Recurso não encontrado -> {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
