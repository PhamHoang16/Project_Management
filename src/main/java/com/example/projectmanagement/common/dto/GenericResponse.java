package com.example.projectmanagement.common.dto;

import com.example.projectmanagement.common.enumeration.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.requests.ApiError;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {
    private boolean isSuccess;

    private MessageEnum message;

    private T data;
}
