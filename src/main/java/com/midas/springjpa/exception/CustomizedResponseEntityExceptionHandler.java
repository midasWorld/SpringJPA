package com.midas.springjpa.exception;

import com.midas.springjpa.web.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    return new ResponseEntity<>(ApiResponse.error(ex, request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String errorMessage = ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
    return new ResponseEntity<>(ApiResponse.error("Validation Failed", errorMessage), HttpStatus.BAD_REQUEST);
  }
}
