package com.project.blogapp.exception;

import com.project.blogapp.payload.ApiResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    //This is a Custom Exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
            String message = e.getMessage();
            ApiResponse apiResponse = new ApiResponse(message,false);

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArguementNotValidExceptionHandler(MethodArgumentNotValidException e){

        Map<String,String> errorMap = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName,message);
        });

        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e){

        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.METHOD_NOT_ALLOWED);
    }

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<ApiResponse> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e){
//
//        String message = e.getMessage();
//        ApiResponse apiResponse = new ApiResponse("Pass Valid Values",false);
//
//        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ApiResponse> numberFormatExceptionHandler(NumberFormatException e){

        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse("Not a Valid Values as Failed To Convert To Number "+message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiResponse> propertyReferenceExceptionHandler(PropertyReferenceException e){
        String message = e.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiResponse> multiPartExceptionHandler(MultipartException e){
        ApiResponse multiPartError = new ApiResponse("File Not Found",false);

        return new ResponseEntity<>(multiPartError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse> fileNotFoundExceptionHandler(FileNotFoundException e){

        return new ResponseEntity<>(new ApiResponse("The system cannot find the file specified",false),HttpStatus.BAD_REQUEST);
    }
}
