package com.example.projectmanagement.common.response;

import com.example.projectmanagement.common.request.PagingRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class PagingResponse<T> {
    private List<T> contents;

    private long totalElements;

    private PagingRequest pagingRequest;
}
