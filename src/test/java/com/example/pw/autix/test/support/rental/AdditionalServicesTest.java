package com.example.pw.autix.test.support.rental;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;

import com.example.pw.autix.TestBase;
import com.example.pw.autix.page.support.SupportPage;
import org.testng.annotations.Test;

public class AdditionalServicesTest extends TestBase {

  /*
      2 ) na stránce "Centrum pomoci" https://help.autix.eu/cs vyhledat termín „autopůjčovna" -> ověřit,
          že to najde stránku „doplňkové služby“, tu otevřit a zkontrolovat, že souhlasí nadpis stránky
   */

  @Test(description = "Navigate to Additional Services page")
  public void additionalServices() {
    getPage().navigate(SupportPage.URL);

    new SupportPage()
        .clickCarRentalBtn()
        .clickAdditionalServicesBtn()
        .additionalServicesHeaderShouldBeVisible();
  }

}
