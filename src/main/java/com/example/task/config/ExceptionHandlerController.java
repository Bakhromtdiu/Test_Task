package com.example.task.config;

import com.example.task.exception.AccessDeniedException;
import com.example.task.exception.BadRequestException;
import com.example.task.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handlerExc(RuntimeException runtimeException) {
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handlerAccessDenied(RuntimeException runtimeException) {
        return ResponseEntity.status(403).body(runtimeException.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handlerBadRequest(RuntimeException runtimeException) {
        return ResponseEntity.status(400).body(runtimeException.getMessage());
    }
}