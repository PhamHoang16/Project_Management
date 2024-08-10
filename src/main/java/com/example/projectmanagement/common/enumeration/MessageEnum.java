package com.example.projectmanagement.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum MessageEnum {
    SUCCESS("200", "Success"),
    USER_NOT_FOUND("M001", "User not found"),
    USER_ALREADY_EXISTS("M002", "User already exists"),
    USER_NOT_AUTHORIZED("M003", "User not authorized"),
    INVALID_CREDENTIALS("M004", "Invalid credentials"),
    ROLE_NOT_FOUND("M010", "Role not found");

    private final String messageCode;
    private final String message;

    MessageEnum(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }
}
