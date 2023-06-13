package com.example.pw.autix.exception;

import static java.lang.String.format;

public class SystemUtilsException extends RuntimeException {

  public SystemUtilsException(final String message, final Object... messageParams) {
    super(format(message, messageParams));
  }

}
