package com.example.pw.autix.test;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;
import static com.example.pw.autix.enums.Localization.CS;
import static com.example.pw.autix.enums.Localization.EN;

import com.example.pw.autix.TestBase;
import com.example.pw.autix.enums.Localization;
import com.example.pw.autix.page.HomePage;
import com.example.pw.autix.page.NavigationMenu;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

  /*
      1) přepnutí jazyka z EN na CZ a kontrola, že je text na webu v požadovaném jazyku (kontrola obsahu stránky)
   */

  @Test(dataProvider = "getTestData", description = "Switch localization EN to CS on Home page")
  public void switchLocalization(Localization fromLocalization, Localization toLocalization) {
    getPage().navigate(HomePage.URL + (EN.equals(fromLocalization) ? "" : fromLocalization.getIsoCode()));

    new NavigationMenu().switchLanguage(toLocalization);
    new HomePage().checkLanguage(toLocalization);
  }

  @DataProvider
  private Object[][] getTestData() {
    return new Object[][]{
        //{fromLocalization, toLocalization}
        {EN, CS},
        {CS, EN}
    };
  }

}
