package com.miniproj.ProductService.controller;

import com.miniproj.ProductService.dto.ResponseObject;
import com.miniproj.ProductService.exception.GenericException;
import com.miniproj.ProductService.exception.InvalidRequestException;
import com.miniproj.ProductService.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequestMapping("api")
public class AbstractController {

    protected <T> ResponseEntity<ResponseObject> sendResponse(T response, HttpStatus httpStatus){
        ResponseObject responseObj = new ResponseObject(response,httpStatus);
        return new ResponseEntity<>(responseObj,httpStatus);
    }

    protected <T> ResponseEntity<ResponseObject> sendSuccessResponse(T response) {
        return sendResponse(response, HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseObject> sendCreatedResponse(T response) {
        return sendResponse(response, HttpStatus.CREATED);
    }

    protected ResponseEntity<ResponseObject> sendNoContentResponse() {
        return sendResponse("No Content", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseObject> handleProductNotFoundException(ResourceNotFoundException exception){
        log.error("Product not found exception: {}",exception.getMessage());
        return sendResponse(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ResponseObject> handleInvalidRequestException(InvalidRequestException exception){
        log.error("Invalid request exception: {}",exception.getMessage());
        return sendResponse(exception.getMessage(), exception.getHttpCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseObject> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        log.error("HTTP message not readable exception: {}",exception.getMessage());
        return sendResponse("Invalid input format",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseObject> handleConstraintViolationException(ConstraintViolationException exception){
        log.error("Constraint violation exception: {}", exception);
        return sendResponse("Validation error: " +exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseObject> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        log.error("Method argument type mismatch exception: {}", exception.getMessage());
        return sendResponse("Invalid argument type", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseObject> handleGenericException(Exception exception) {
        log.error("Generic exception: {}", exception.getMessage());
        return sendResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
