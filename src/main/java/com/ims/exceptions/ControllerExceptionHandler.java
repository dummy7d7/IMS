package com.ims.exceptions;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
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
  
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//  public void globalExceptionHandler(Exception ex, WebRequest request,HttpSession session) 
//  {
//	  System.out.println("entered during error controller for errors from service layer");
//    ErrorMessage message = new ErrorMessage(
//        HttpStatus.INTERNAL_SERVER_ERROR.value(),
//        new Date(),
//        ex.getMessage(),
//        request.getDescription(false));
//  
//    return message;
//  }
  
//  @ExceptionHandler(Exception.class)
//  public RedirectView handleMyException(
//                               HttpServletRequest request,
//                               HttpServletResponse response) throws IOException {
////      String redirect = getRedirectUrl("/logout");
//System.out.println("entered exception handler logout resolver");
//      RedirectView rw = new RedirectView("/login");
////      rw.setStatusCode(HttpStatus.MOVED_PERMANENTLY); // you might not need this
//      FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
//      if (outputFlashMap != null){
//          outputFlashMap.put("myAttribute", true);
//      }
//      return rw;
//  }
}