package pages;

import constants.Constant;
import data.Sports;
import org.openqa.selenium.WebElement;
import util.Pages;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.LocatorUtil.loc;
import static util.LoggerManager.log;

public class DropdownPage extends Pages {
    public DropdownPage openAndLoadDropdownHomePage(String url) {
        openAndLoadHomePage(url, loc("dialogsHomePage"));
        return this;
    }

    public DropdownPage chooseAutoCompleteSport(String autoCompleteSport) {
        enterAutoCompleteSport(autoCompleteSport);
        waitForLoad(loc("autoCompleteSportDropdownContainer"), 3, "Dropdown list does NOT visible!");
        selectAutoCompleteSportFromDropdown(autoCompleteSport);
        return this;
    }

    private void enterAutoCompleteSport(String text) {
        enterInputField(loc("autoCompleteInputField"), text);
    }

    private void selectAutoCompleteSportFromDropdown(String autoCompleteSport) {
        waitForLoad(loc("span_text_parent_li", autoCompleteSport), 5);
        clickOnElement(loc("span_text_parent_li", autoCompleteSport), "Unable to click on " + autoCompleteSport + " dropdown element");
        waitForNotVisible(loc("autoCompleteSportDropdownContainer"), 3);
    }

    public DropdownPage verifyAutoCompleteSport(String autoCompleteSport) {
        assertEquals(autoCompleteSport, getElementValue(loc("autoCompleteInputField")), "Favorite sport input field text does NOT match with actual");
        log("Favorite sport successfully entered in AutoComplete input field");
        return this;
    }

    public DropdownPage clearAutoCompleteInputField() {
        clickOnAutoCompleteInputField();
        verifyClearAutoCompleteInputField();
        log("Auto complete successfully cleared");
        return this;
    }

    private void verifyClearAutoCompleteInputField() {
        assertEquals("", getElementValue(loc("autoCompleteInputField")), "Favorite sport input field text does NOT empty");
    }

    private void clickOnAutoCompleteInputField() {
        clickOnElement(loc("autoCompleteClearButton"), "Unable to click on the clear button for auto complete sport");
    }

    public DropdownPage verifyMultiSelectElement() {
        openMultiSelectDropdown();
        verifyDropdownListElements(loc("li_class", Constant.ITEM), "span_text_parent_li", Sports.MULTI_SELECT_SPORTS);
        log("Sports list match");
        return this;
    }

    private void openMultiSelectDropdown() {
        clickOnMultiSelectInputField();
        waitForLoadPopup();
    }

    private void clickOnMultiSelectInputField() {
        clickOnElement(loc("multiSelectInputField"), "Unable to click on MultiSelect input field");
    }

    public DropdownPage clearMultiSelectInputField() {
        List<WebElement> clearButtons = getDriver().findElements(loc("multiCompleteClearButtons"));
        for (int i = clearButtons.size(); i >= 1; i--) {
            clickOnElement(loc("multiCompleteClearButton", String.valueOf(i)));
            waitForNotVisible(loc("multiCompleteClearButton", String.valueOf(i)), 3);
        }
        log("Multi select input field is empty");
        return this;
    }

    public DropdownPage chooseTennisInMultiSelectInputField() {
        enterMultiSelectSport(Sports.TENNIS);
        verifySelectedMultiSelectSport(Sports.TENNIS);
        return this;
    }

    public DropdownPage chooseFootbalInMultiSelectInputField() {
        enterMultiSelectSport(Sports.FOOTBALL);
        verifySelectedMultiSelectSport(Sports.FOOTBALL);

        return this;
    }

    private void enterMultiSelectSport(String text) {
        enterInputField(loc("multiSelectInputField"), text);
        pressEnterOnActiveElement();
    }

    private void verifySelectedMultiSelectSport(String sport) {
        if (sport.equals(Sports.TENNIS)) {
            assertTrue(getTextElement(loc("div_class", Constant.VALUE)).contains("\n" + sport) || getTextElement(loc("div_class", Constant.VALUE)).startsWith(sport));
        } else {
            assertTrue(getTextElement(loc("div_class", Constant.VALUE)).contains(sport));
        }
        log(sport + " is entered in Multi Select input field");
    }
}
