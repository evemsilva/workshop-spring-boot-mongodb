package com.nelioalves.workshopmongo.resources.exception;

import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(HttpServletRequest request, ObjectNotFoundException exception){
	HttpStatus status = HttpStatus.NOT_FOUND;
	StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
