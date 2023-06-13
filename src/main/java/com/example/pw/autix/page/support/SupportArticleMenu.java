package com.example.pw.autix.page.support;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;

import com.example.pw.autix.page.base.MasterPage;
import com.microsoft.playwright.Locator;

public class SupportArticleMenu extends MasterPage {

  private static Locator additionalServicesBtn;

  public SupportArticleMenu() {
    additionalServicesBtn = getPage().locator("//a[.='Doplňkové služby']");
  }

  public AdditionalServicesPage clickAdditionalServicesBtn() {
    additionalServicesBtn.click();
    return new AdditionalServicesPage();
  }

}
