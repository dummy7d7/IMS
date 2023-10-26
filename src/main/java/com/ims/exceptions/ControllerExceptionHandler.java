package com.ims.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return message;
  }
  
  
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, List<String>>> handleValidationErrors(ConstraintViolationException ex)
  {
//	  System.err.println(ex.getConstraintViolations());
      List<String> errors = ex.getConstraintViolations()
              .stream().map(t ->t.getMessage() ).collect(Collectors.toList());
      return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  private Map<String, List<String>> getErrorsMap(List<String> errors) {
      Map<String, List<String>> errorResponse = new HashMap<>();
      errorResponse.put("errors", errors);
      return errorResponse;
  }
  
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
//	  System.err.println("jncsd bjcfnhakdsvdakhnvanhikvdhnv adljadn;kvnk; vaa");
//	  ErrorMessage message = new ErrorMessage(
//        HttpStatus.INTERNAL_SERVER_ERROR.value(),
//        new Date(),
//        ex.getMessage(),
//        request.getDescription(false));
//    
//    return message;
//  }
}