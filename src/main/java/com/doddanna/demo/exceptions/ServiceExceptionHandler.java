package com.doddanna.demo.exceptions;

import com.doddanna.demo.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseModel userNotFoundException(UserNotFoundException userNotFoundException){
        return ResponseModel
                .builder()
                .message(userNotFoundException.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel badRequest(BadRequestException badRequestException){
        return ResponseModel
                .builder()
                .message(badRequestException.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseModel commonErrorHandling(Exception exception){
        return ResponseModel
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
