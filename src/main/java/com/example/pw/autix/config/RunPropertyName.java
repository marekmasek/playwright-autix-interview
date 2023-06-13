package com.example.pw.autix.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum RunPropertyName {

  ENV("env"),
  URL("url"),
  SUPPORT_URL("support_url");

  @Getter
  private final String value;
}
