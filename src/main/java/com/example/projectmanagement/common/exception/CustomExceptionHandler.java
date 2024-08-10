//package com.example.projectmanagement.common.exception;
//
//import com.example.projectmanagement.common.dto.GenericResponse;
//import com.example.projectmanagement.common.enumeration.MessageEnum;
//import com.example.projectmanagement.usermanagement.exception.UserManagementException;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//    @ExceptionHandler(UserManagementException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<GenericResponse<Object>> userManagementExceptionHandle(UserManagementException e, HttpServletRequest request) {
//        GenericResponse<Object> response = GenericResponse
//                .builder()
//                .isSuccess(false)
//                .message(MessageEnum.valueOf(e.getMessage()))
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
//}
