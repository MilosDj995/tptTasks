package pages;

import util.Pages;

import static util.LocatorUtil.loc;

import static util.LoggerManager.*;


public class DialogsPage extends Pages {

    public DialogsPage openAndLoadDialogsHomePage(String url) {
        openAndLoadHomePage(url, loc("dropdownHomePage"));
        log("Home page is loaded succesfull");
        return this;
    }

    public ConfirmDialog clickOnOpenDialogButton() {
        clickOnElement(loc("openDialogButton"), "Unable to click on the Open dialog button");
        return new ConfirmDialog();
    }

    public AboutDialog clickOnOpenWindowDialogButton() {
        clickOnElement(loc("windowDialogButton"), "Unable to click on the Window dialog button");
        return new AboutDialog();
    }

    public DialogsPage verifyDialogIsNotVisible() {
        verifyElementIsNotVisible(loc("dialogContainer"), 5, "Dialog still Visible");
        log("Dialog is Not visible");
        return this;
    }
}
