package com.vivek.NoteTaker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotAuthorizedUserException.class)
    public ResponseEntity<String> handleNotAuthorizedUserException(NotAuthorizedUserException notAuthorizedUserException){
        return new ResponseEntity<>(notAuthorizedUserException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFoundException(NoteNotFoundException noteNotFoundException){
        return new ResponseEntity<>(noteNotFoundException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleNoteNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getErrorMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
