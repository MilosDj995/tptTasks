package util;

import Constants.Constant;
import config.ConfigurationStrings;
import org.openqa.selenium.WebDriver;

import static util.LoggerManager.*;
import static util.LocatorUtil.loc;

public class Dialog extends Base {

    public Dialog() {
        super();
    }

    public void verifyDialogIsVisible() {
        verifyElementIsVisible(loc("dialogContainer"), 5, "Dialog is Not Visible");
        log("Dialog is visible");
    }

    public void verifyMinimizeButton() {
        verifyIsVisibleSvgIconByTitle(Constant.MINIMIZE);
        log("Minimize Button is visible");
    }

    public void verifyMaximizeButton() {
        verifyIsVisibleSvgIconByTitle(Constant.MAXIMIZE);
        log("Maximize Button is visible");
    }

    public void verifyCloseButton() {
        verifyIsVisibleSvgIconByTitle(Constant.CLOSE);
        log("Close Button is visible");
    }

    public void maximizeDialog() {
        clickOnMaximizeButton();
        verifyMaximizeButtonIsNotVisible();
    }

    private void verifyIsVisibleSvgIconByTitle(String titleOfElement) {
        verifyElementIsVisible(loc("button_title", titleOfElement), 3);
    }

    public void clickOnMaximizeButton() {
        clickOnElement(loc("button_title", Constant.MAXIMIZE), "Unable to click on Maximize button");
        waitForLoad(loc("maximizedDialogContainer"), 5);
        log("About Window is maximized");
    }

    public void closeDialog() {
        clickOnCloseButtonFromDialog();
        waitForNotVisible(loc("dialogContainer"), 5);
    }

    public void verifyMaximizeButtonIsNotVisible() {
        verifyElementIsNotVisible(loc("button_title", Constant.MAXIMIZE), 5, "Maximize button still visible");
        log("Maximize button is NOT visible");
    }

    public void clickOnCloseButtonFromDialog() {
        clickOnElement(loc("dialogCloseButton"), "Unable to click on Close button");
    }

    public void hoverOnCloseButtonFromDialog() {
        hoverOnElement(loc("dialogCloseButton"), "Unable to hover on Close button");
    }
}
