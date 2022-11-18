 package com.jooqgenerate.demo.exceptions;

 import com.jooqgenerate.demo.models.ApiResponse;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ApiResponse bookNotFoundExceptionHandler(BookNotFoundException exception){
        String msg = exception.getMessage();
        System.out.println("Inside exception handler : " + msg);
        ApiResponse apiResponse = new ApiResponse(msg, "false");
        return apiResponse;
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse allExceptionHandler(Exception ex){
        return new ApiResponse("From All Exception Handler : "+ex.getMessage(), "false");
    }
}
