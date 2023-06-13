package com.example.pw.autix.page.support;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;
import static com.example.pw.autix.config.TestProperties.getSupportUrl;

import com.example.pw.autix.page.HomePage;
import com.example.pw.autix.page.base.MasterPage;
import com.microsoft.playwright.Locator;

public class SupportPage extends MasterPage {

  public static final String URL = getSupportUrl();

  private static Locator carRentalBtn, backToAppBtn;

  public SupportPage() {
    carRentalBtn = getPage().locator("//h2[.='Autopůjčovna']");
    backToAppBtn = getPage().locator("//div[contains(@class,'max-w-[90rem]')]//a[contains(@href,'autix.eu')]");
  }

  public SupportArticleMenu clickCarRentalBtn() {
    carRentalBtn.click();
    return new SupportArticleMenu();
  }

  public HomePage clickBackToAppBtn() {
    backToAppBtn.click();
    return new HomePage();
  }

}
