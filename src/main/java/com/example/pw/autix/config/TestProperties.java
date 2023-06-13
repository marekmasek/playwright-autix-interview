package com.example.pw.autix.config;

import static com.example.pw.autix.config.RunPropertyName.ENV;
import static com.example.pw.autix.config.RunPropertyName.SUPPORT_URL;
import static com.example.pw.autix.config.RunPropertyName.URL;
import static com.example.pw.autix.utils.ParamString.paramString;
import static com.example.pw.autix.utils.SystemUtils.getEnvSysVar;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.math.NumberUtils.createInteger;

import com.example.pw.autix.exception.TestPropertiesException;
import com.example.pw.autix.utils.ParamString;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestProperties {

  private static final ParamString PROPERTIES_NAME = paramString("environment/%s.properties");
  private static Properties testProperties;
  private static String env;
  private static String url;
  private static String supportUrl;

  private static void setEnv() {
    env = getEnvSysVar(ENV.getValue()).toLowerCase();
  }

  private static void setBaseUrl() {
    url = requireNonNull(getProperty(URL.getValue(), true)).toLowerCase();
  }

  private static void setSupportUrl() {
    supportUrl = requireNonNull(getProperty(SUPPORT_URL.getValue(), true)).toLowerCase();
  }

  public static String getEnv() {
    return env;
  }

  public static String getBaseUrl() {
    return url;
  }

  public static String getSupportUrl() {
    return supportUrl;
  }

  public static void initTestProperties() {
    log.info("Setting common ENV properties");
    setEnv();
    setBaseUrl();
    setSupportUrl();
  }

  public static String getProperty(String propertyKey) {
    return getProperty(propertyKey, false);
  }

  public static String getProperty(String propertyKey, boolean required) {
    if (testProperties == null || testProperties.isEmpty()) {
      loadProperties();
    }

    if (testProperties.containsKey(propertyKey)) {
      return testProperties.getProperty(propertyKey);
    } else {
      String notFoundMessage = "Property key [%s] not found in properties file";

      if (required) {
        throw new TestPropertiesException(notFoundMessage, propertyKey);
      } else {
        log.warn(format(notFoundMessage, propertyKey));
        return null;
      }
    }
  }

  public static boolean getBoolProperty(String propertyKey) {
    return getBoolProperty(propertyKey, false);
  }

  public static boolean getBoolProperty(String propertyKey, boolean required) {
    return Boolean.parseBoolean(getProperty(propertyKey, required));
  }

  public static Integer getIntProperty(String propertyKey) {
    return getIntProperty(propertyKey, false);
  }

  public static Integer getIntProperty(String propertyKey, boolean required) {
    return createInteger(getProperty(propertyKey, required));
  }

  private static void loadProperties() {
    if (env == null) {
      throw new IllegalStateException(
          "Cannot load properties file, because environment property is not set. Example: -Denv=dev");
    }
    testProperties = new Properties();
    String propertiesFileName = PROPERTIES_NAME.getCompleted(env);

    try {
      InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream(propertiesFileName);
      testProperties.load(inputStream);
    } catch (IOException e) {
      throw new TestPropertiesException("Could not load environment properties file [%s]", e, propertiesFileName);
    }
  }

}
