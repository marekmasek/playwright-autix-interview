package com.example.pw.autix.page;

import static com.example.pw.autix.config.PlaywrightConfig.getPage;

import com.example.pw.autix.enums.Localization;
import com.example.pw.autix.page.base.MasterPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NavigationMenu extends MasterPage {

  private static Locator languageSwitcher, language;

  public NavigationMenu() {
    languageSwitcher = getPage().locator("//div[contains(@class,'trp-language-switcher')]");
    language = getPage().getByRole(AriaRole.LINK); //a
  }

  public NavigationMenu switchLanguage(Localization localization) {
    log.info("Switching language to: " + localization.name());
    languageSwitcher.hover();
    language.getByTitle(localization.getValue()).click();
    return this;
  }
}
