package com.example.pw.autix.utils;

import com.example.pw.autix.exception.SystemUtilsException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@UtilityClass
public class SystemUtils {

  /**
   * Method reads Environment Variable and System Property. If it's required, and it's missing, it will throw an
   * Exception!
   */
  public static String getEnvSysVar(String name) {
    String result;

    var property = System.getProperty(name);
    var envVariable = System.getenv(name);

    if (StringUtils.isNotBlank(property)) {
      result = property;
    } else if (StringUtils.isNotBlank(envVariable)) {
      result = envVariable;
    } else {
      throw new SystemUtilsException("Variable: %s is not set as 'System variable' or 'Java property'", name);
    }
    return result;
  }
}
