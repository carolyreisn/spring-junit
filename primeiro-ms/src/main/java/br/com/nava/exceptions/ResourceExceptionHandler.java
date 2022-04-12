package br.com.nava.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	// o primeiro parâmetro é a exceção original e 
	// o segundo parâmetro, a requisição com os seus dados
	public ResponseEntity<StandardError> methodValidation(MethodArgumentNotValidException e,HttpServletRequest request) {
		
		StandardError error = new StandardError();
		error.setTimestamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setError("Erro de validação");
		error.setMessage("Error ao validar a operação");
		error.setPath(request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
		
		@ExceptionHandler(EmptyResultDataAccessException.class)
		// o primeiro parâmetro é a exceção original e 
		// o segundo parâmetro, a requisição com os seus dados
		public ResponseEntity<StandardError> methodValidation(EmptyResultDataAccessException e,HttpServletRequest request) {
			
			StandardError error = new StandardError();
			error.setTimestamp(System.currentTimeMillis());
			error.setStatus(HttpStatus.OK.value());
			error.setError("Erro de validação");
			error.setMessage("Error ao validar a operação");
			error.setPath(request.getRequestURI());
			
			return ResponseEntity.status(HttpStatus.OK).body(error);
	}

}
