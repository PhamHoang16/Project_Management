package com.example.projectmanagement.common.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PagingRequest {
    @Min(value = 0)
    private int page;
    @Min(value = 1)
    private int size;

    List<SortRequest> propertiesSort = new ArrayList<>();

    public PagingRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
