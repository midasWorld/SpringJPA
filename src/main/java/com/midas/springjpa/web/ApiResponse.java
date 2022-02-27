package com.midas.springjpa.web;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
  private final boolean success;
  private final T response;

  public ApiResponse(boolean success, T response) {
    this.success = success;
    this.response = response;
  }

  public static <T> ApiResponse<T> ok(T response) {
    return new ApiResponse<>(true, response);
  }

  public static ApiResponse<?> error(String message) {
    return new ApiResponse<>(false, new ApiError(message, null));
  }

  public static ApiResponse<?> error(String message, String details) {
    return new ApiResponse<>(false, new ApiError(message, details));
  }

  public static ApiResponse<?> error(Throwable throwable, String details) {
    return new ApiResponse<>(false, new ApiError(throwable.getMessage(), details));
  }
}
