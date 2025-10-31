package com.example.taller_CI.DC.Util;

import com.example.taller_CI.DC.Util.Exceptions.ChefNotFoundException;
import com.example.taller_CI.DC.Util.Exceptions.RecetaNotFoundException;
import com.example.taller_CI.DC.Util.Exceptions.TemporadaNullException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = "com.example.taller_CI")
public class GlobalExceptionHandler {

    @ExceptionHandler(ChefNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleChefNotFound(ChefNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Chef no encontrado");
        problem.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(TemporadaNullException.class)
    public ResponseEntity<ProblemDetail> handleTemporadaNull(TemporadaNullException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Temporada nula ");
        problem.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(RecetaNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleRecetaNotFound(RecetaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Receta no encontrada");
        problem.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGlobalException(Exception ex, HttpServletRequest request) {
        if (request.getRequestURI().contains("/v3/api-docs")) {
            throw new RuntimeException(ex);
        }

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle("Error interno del servidor");
        problem.setDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}