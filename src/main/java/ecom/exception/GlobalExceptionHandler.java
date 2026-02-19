package ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Handle generic errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.FORBIDDEN.value());
        error.put("error", "Action not allowed");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    
    // Handle Insufficient Stock
    @ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<Map<String, Object>> handleInsufficientStock(InsufficientStockException ex) {
		Map<String, Object> error = new HashMap<>();
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", "Insufficient stock");
		error.put("message", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
    
    // Handle Cart Empty
    @ExceptionHandler(CartEmptyException.class)
        public ResponseEntity<Map<String, Object>> handleCartEmpty(CartEmptyException ex) {
			Map<String, Object> error = new HashMap<>();
			error.put("timestamp", LocalDateTime.now());
			error.put("status", HttpStatus.BAD_REQUEST.value());
			error.put("error", "Cart is empty");
			error.put("message", ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

