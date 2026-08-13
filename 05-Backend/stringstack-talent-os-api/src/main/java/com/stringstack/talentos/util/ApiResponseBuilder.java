package com.stringstack.talentos.util;

import com.stringstack.talentos.exception.ApiResponse;

import java.time.LocalDateTime;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();
    }
}