package com.example.pw.autix.exception;

import static java.lang.String.format;

public class TestPropertiesException extends RuntimeException {

  public TestPropertiesException(final String message, final Object... messageParams) {
    super(format(message, messageParams));
  }

  public TestPropertiesException(final String message, final Exception e, final Object... messageParams) {
    super(format(message, messageParams), e);
  }

}
