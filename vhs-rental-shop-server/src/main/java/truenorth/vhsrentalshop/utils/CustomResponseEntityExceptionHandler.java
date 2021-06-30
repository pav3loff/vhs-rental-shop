package truenorth.vhsrentalshop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<?> handleException(MethodArgumentNotValidException exc) {
		ObjectError objectError = exc.getAllErrors().get(0);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(objectError.getDefaultMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<?> handleException(HttpRequestMethodNotSupportedException exc) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(String.format("HTTP %s is not allowed!", exc.getMethod()));
	}

	@ExceptionHandler
	public ResponseEntity<?> handleException(HttpMessageNotReadableException exc) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid arguments!");
	}
	
}
