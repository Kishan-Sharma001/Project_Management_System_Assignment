package com.sirma.projectManagementSystem.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sirma.projectManagementSystem.exception.ProjectNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ProjectNotFoundException.class})
    public ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
        
      
    }
}