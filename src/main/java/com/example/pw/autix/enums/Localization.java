package com.example.pw.autix.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Localization {

  EN("English", "en", "en-GB"),
  CS("Czech", "cs", "cs-CZ"),
  PL("Polish", "pl", "pl-PL");

  @Getter
  private final String value;
  @Getter
  private final String isoCode;
  @Getter
  private final String htmlLang;

}
