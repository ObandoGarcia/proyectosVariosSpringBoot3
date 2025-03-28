package com.obando.product.service.controllers;

import com.obando.product.service.exceptions.CategoryNotFoundException;
import com.obando.product.service.exceptions.ProductNotFoundException;
import com.obando.product.service.models.dtos.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.obando.product.service.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponse handleProductNotFoundException(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(PRODUCT_NOT_FOUND.getCode());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(PRODUCT_NOT_FOUND.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleCategoryNotFoundException(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(CATEGORY_NOT_FOUND.getCode());
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(CATEGORY_NOT_FOUND.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception)
    {
        //Validation errors
        BindingResult bindingResult = exception.getBindingResult();


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(INVALID_PRODUCT.getCode());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage(INVALID_PRODUCT.getMessage());
        errorResponse.setDetailsMessages(
                bindingResult.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList())
        );
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleInternalServerError(Exception exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(GENERIC_ERROR.getCode());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(GENERIC_ERROR.getMessage());
        errorResponse.setDetailsMessages(Collections.singletonList(exception.getMessage()));
        errorResponse.setTimeStamp(LocalDateTime.now());
        return errorResponse;
    }
}
