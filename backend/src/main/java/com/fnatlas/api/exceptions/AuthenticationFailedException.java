package com.fnatlas.api.exceptions;

public class AuthenticationFailedException extends RuntimeException {

  public AuthenticationFailedException(String message) {
    super(message);
  }

  public AuthenticationFailedException() {
    super("Invalid username or password");
  }
}