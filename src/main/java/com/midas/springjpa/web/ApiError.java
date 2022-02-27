package com.midas.springjpa.web;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiError {
  private final LocalDateTime timestamp;
  private final String message;
  private final String details;

  public ApiError(String message, String details) {
    this.message = message;
    this.details = details;
    this.timestamp = LocalDateTime.now();
  }
}
