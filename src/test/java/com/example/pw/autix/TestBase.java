package com.example.pw.autix;

import static com.example.pw.autix.config.PlaywrightConfig.closePlaywright;
import static com.example.pw.autix.config.PlaywrightConfig.configPlaywright;
import static com.example.pw.autix.config.TestProperties.initTestProperties;
import static com.example.pw.autix.utils.SoftAssertHelper.clearSoftAssert;
import static com.example.pw.autix.utils.SoftAssertHelper.getSoftAssert;
import static org.testng.ITestResult.FAILURE;

import com.example.pw.autix.listener.TestLogListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Slf4j
@Listeners(value = {TestLogListener.class})
public abstract class TestBase implements IHookable {

  @BeforeSuite(alwaysRun = true)
  public void initTestData() {
    initTestProperties();
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    configPlaywright();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
    closePlaywright();
  }

  @Override
  public void run(IHookCallBack callBack, ITestResult result) {
    callBack.runTestMethod(result);
    SoftAssert softAssert = getSoftAssert();
    clearSoftAssert();
    try {
      softAssert.assertAll();
    } catch (AssertionError e) {
      result.setThrowable(e);
      result.setStatus(FAILURE);
    }
  }

}
