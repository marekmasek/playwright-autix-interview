package com.example.pw.autix.test.support;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;
import static com.example.pw.autix.enums.Localization.CS;
import static com.example.pw.autix.enums.Localization.EN;

import com.example.pw.autix.TestBase;
import com.example.pw.autix.enums.Localization;
import com.example.pw.autix.page.support.SupportPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SupportTest extends TestBase {

  /*
      3 ) na stránce "Centrum pomoci" https://help.autix.eu/cs kliknout z hlavní stránky na odkaz vpravo nahoře,
          který směřuje na autix.eu a udělat kontrolu na url. To samé pro CZ a EN mutaci.
   */

  @Test(dataProvider = "getTestData", description = "Go from Support page back to Home page")
  public void goBackToApp(Localization localization) {
    getPage().navigate(SupportPage.URL + localization.getIsoCode());

    new SupportPage()
        .clickBackToAppBtn()
        .verifyUrl(localization);
  }

  @DataProvider
  private Object[][] getTestData() {
    return new Object[][]{
        //{localization}
        {CS},
        {EN}
    };
  }

}
