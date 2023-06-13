package com.example.pw.autix.utils;

import static java.lang.String.format;

public class ParamString {

  private final String string;

  public ParamString(String string) {
    this.string = string;
  }

  public static ParamString paramString(String paramString) {
    return new ParamString(paramString);
  }

  public String getCompleted(Object... stringParams) {
    return format(string, stringParams);
  }
}
