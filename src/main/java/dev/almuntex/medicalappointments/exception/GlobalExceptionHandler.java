package dev.almuntex.medicalappointments.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.util.ParsingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handleDoctorNotFound(EntityNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("category", ExceptionCategory.GENERIC);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                "Validation failed with " + exception.getFieldErrorCount() + " error(s).");
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("category", ExceptionCategory.VALIDATION);
        problemDetail.setProperty("field_errors", handleFieldErrors(exception));
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    private static Map<String, List<String>> handleFieldErrors(MethodArgumentNotValidException exception) {
        Map<String, List<String>> result = new HashMap<>();
        for (FieldError fe : exception.getFieldErrors()) {
            String key = ParsingUtils.reconcatenateCamelCase(fe.getField(), "_");
            result.computeIfAbsent(key, k -> new ArrayList<>()).add(fe.getDefaultMessage());
        }
        return result;
    }
}
