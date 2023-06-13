package com.example.pw.autix.listener;

import com.example.pw.autix.config.TestProperties;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestLogListener implements ITestListener {

  @Override
  public void onTestFailure(ITestResult tr) {
    log.error("""

            |||||||||||||||||||||||||||||||||||||||||||||
            TEST METHOD FAILED {}
               ->   BECAUSE OF {}
            |||||||||||||||||||||||||||||||||||||||||||||""",
        tr.getName(),
        tr.getThrowable().toString());
  }

  @Override
  public void onStart(ITestContext tc) {
    log.info("""

            =============================================
            STARTING {} on environment: {}
            =============================================""",
        tc.getName(), TestProperties.getEnv());
  }

  @Override
  public void onTestStart(ITestResult tr) {
    var params = tr.getParameters();
    log.info("""

            ---------------------------------------------
            STARTING TEST METHOD {}{}
            ---------------------------------------------""",
        tr.getMethod().getMethodName(),
        params.length < 1 ? "" : "\n  -> WITH PARAMETERS " + Arrays.toString(params));
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    log.info("""

            ---------------------------------------------
            TEST METHOD SUCCESS {}
            ---------------------------------------------""",
        tr.getName());
  }
}
