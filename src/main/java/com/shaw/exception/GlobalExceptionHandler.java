package com.shaw.exception;

import java.net.BindException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.shaw.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse<Void>> resourceNotFoundHandler(ResourceNotFound ex) {
	    String message = ex.getMessage();
	    ApiResponse<Void> apiResponse = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), message, null, null);
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}

	

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex, WebRequest request) {
        // Extract the error message from the exception
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";

        // Extract the request path
        String requestPath = request.getDescription(false);

        // Create an ApiResponse object with the extracted error message and request path
        ApiResponse<Void> apiResponse = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                errorMessage,
                null,
                URI.create(requestPath)
        );

        // Return the ApiResponse in the response entity
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<Map<String, String>> handleBindExceptions(BindException ex) {
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError error : ex.getFieldErrors()) {
//            String fieldName = error.getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        }
//        return ResponseEntity.badRequest().body(errors);
//    }
}
