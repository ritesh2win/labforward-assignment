package com.labforward.assignment.exception;

import static com.labforward.assignment.constant.GenericConstants.ERROR_STATUS;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomApiExceptionHandler {

  private HttpServletRequest httpServletRequest;

  @Autowired
  public CustomApiExceptionHandler(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Fault> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(
            new Fault()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .status(ERROR_STATUS)
                .message("Validation Failed")
                .datetime((new Timestamp(System.currentTimeMillis())).toString()));
  }
}
