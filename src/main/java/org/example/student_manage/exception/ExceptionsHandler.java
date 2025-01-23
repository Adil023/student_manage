package org.example.student_manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(StudentNotFoundException.class)
   public ResponseEntity<Object> handleExceptions(
           StudentNotFoundException ex
   ){
       ApiException e = new ApiException(
               ex.getMessage(),
               HttpStatus.NOT_FOUND
       );

       return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
   }


}
