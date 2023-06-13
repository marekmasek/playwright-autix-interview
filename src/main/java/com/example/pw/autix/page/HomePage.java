package com.example.pw.autix.page;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;
import static com.example.pw.autix.config.TestProperties.getBaseUrl;
import static com.example.pw.autix.enums.Localization.EN;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.example.pw.autix.enums.Localization;
import com.example.pw.autix.page.base.MasterPage;
import com.microsoft.playwright.Locator;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HomePage extends MasterPage {

  public static final String URL = getBaseUrl();
  private static Locator html, textblockElements;

  public HomePage() {
    html = getPage().locator("//html");
    textblockElements = getPage().locator("//div[contains(@id,'text_block')]");
  }

  private static final List<String> TEXTS_EN = List.of(
      "Autix BMS is a modern and comprehensive online system",
      "Employee control and loss minimisation",
      "New car sales agenda and agenda for the sale of used cars in simple way",
      "What are the main differences between off-line and on-line solutions"
  );

  private static final List<String> TEXTS_CZ = List.of(
      "Autix BMS je moderní a komplexní online systém",
      "Kontrola zaměstnanců a minimalizace ztrát",
      "Agenda prodeje nových vozů a agenda prodeje ojetých vozů jednoduchým způsobem",
      "Jaké jsou hlavní rozdíly mezi off-line a on-line řešeními"
  );

  public HomePage checkLanguage(Localization localization) {
    log.info("Checking language of the page");
    assertThat(html).hasAttribute("lang", localization.getHtmlLang());

    List<String> expectedTexts = switch (localization) {
      case CS -> TEXTS_CZ;
      case EN -> TEXTS_EN;
      default -> throw new IllegalStateException("Expected texts are not implemented for this localization, they have to be added");
    };

    assertThat(textblockElements).containsText(expectedTexts.toArray(String[]::new));
    return this;
  }

  public HomePage verifyUrl(Localization localization) {
    log.info("Checking that url is matching the expected localization: " + localization.name());
    String expectedUrl = getBaseUrl();
    expectedUrl = EN.equals(localization) ? expectedUrl : expectedUrl + localization.getIsoCode();
    assertThat(getPage()).hasURL(expectedUrl);
    return this;
  }

}
