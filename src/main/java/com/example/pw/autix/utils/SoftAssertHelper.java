package com.example.pw.autix.utils;

import lombok.experimental.UtilityClass;
import org.testng.asserts.SoftAssert;

@UtilityClass
public class SoftAssertHelper {

  private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

  public static synchronized SoftAssert getSoftAssert() {
    if (softAssert.get() == null) {
      softAssert.set(new SoftAssert());
    }
    return softAssert.get();
  }

  public static void clearSoftAssert() {
    softAssert.set(null);
  }
}
