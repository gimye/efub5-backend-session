package com.practice.efubaccount.handler;

import com.practice.efubaccount.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
//        ErrorResponseDto error = ErrorResponseDto.builder()
//                .message(ex.getMessage())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .build();
//        return ResponseEntity.badRequest().body(error);
//    }
}