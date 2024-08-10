package com.example.projectmanagement.usermanagement.exception;

import com.example.projectmanagement.common.enumeration.MessageEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserManagementException extends RuntimeException {
    private final String messageCode;

    public UserManagementException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
        this.messageCode = messageEnum.getMessageCode();
    }


}
