package com.example.pw.autix.config;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.setDefaultAssertionTimeout;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlaywrightConfig {

  private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();
  private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
  private static final ThreadLocal<BrowserContext> BROWSER_CONTEXT = new ThreadLocal<>();
  private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();


  public static Playwright getPlaywright() {
    return PLAYWRIGHT.get();
  }

  public static Browser getBrowser() {
    return BROWSER.get();
  }

  public static BrowserContext getBrowserContext() {
    return BROWSER_CONTEXT.get();
  }

  public static Page getPage() {
    var page = PAGE.get();
    if (page == null) {
      throw new IllegalStateException("No Page object is created for the current thread. Check if your test class extends TestBase class...");
    }
    return page;
  }

  public static void configPlaywright() {
    PLAYWRIGHT.set(Playwright.create());
    BROWSER.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
    BROWSER_CONTEXT.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080)));
    PAGE.set(getBrowserContext().newPage());
    setDefaultAssertionTimeout(15000L);
  }

  public static void closePlaywright() {
    getPage().close();
    getBrowserContext().close();
    getBrowser().close();
    getPlaywright().close();
  }

}
