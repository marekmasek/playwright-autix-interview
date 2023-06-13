package com.example.pw.autix.page.support;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.example.pw.autix.page.base.MasterPage;
import com.microsoft.playwright.Locator;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AdditionalServicesPage extends MasterPage {

  private static Locator additionalServicesHdr;

  public AdditionalServicesPage() {
    additionalServicesHdr = getPage().locator("//h1[.='Doplňkové služby']");
  }

  public void additionalServicesHeaderShouldBeVisible() {
    log.info("Checking if Additional Services header is visible");
    assertThat(additionalServicesHdr).isVisible();
  }

}
