package pages;

import Constants.Constant;
import Data.Colors;
import util.Dialog;

import static util.LoggerManager.*;
import static util.LocatorUtil.loc;

public class ConfirmDialog extends Dialog {
    public ConfirmDialog() {
        super();
        verifyDialogIsVisible();
    }

    public ConfirmDialog verifyButtonsAreVisible() {
        verifyDialogContentIsVisible();
        verifyBaseButtonIsVisible();
        verifyPrimaryButtonIsVisible();
        log("Elements from the confirm dialog are checked");
        return this;
    }

    public void verifyBaseButtonIsVisible() {
        verifyElementIsVisible(loc("dialogButton", Constant.BASE), 5, "No button from Confirm dialog is not visible");
    }

    public void verifyPrimaryButtonIsVisible() {
        verifyElementIsVisible(loc("dialogButton", Constant.PRIMARY), 5, "Yes button from Confirm dialog is not visible");
    }

    public void verifyDialogContentIsVisible() {
        verifyElementIsVisible(loc("div_class", Constant.DIALOG_CONTENT), 5, "Dialog content is not visible");
    }

    public ConfirmDialog verifyColorOfYesButton() {
        verifyColor(Colors.YES_BUTTON, loc("dialogButton", Constant.PRIMARY));
        log("Yes button has expected color");
        return this;
    }

    public DialogsPage hoverOnXButtonAndPressEnter() {
        hoverOnCloseButtonFromDialog();
        pressEnterOnActiveElement();
        log("Hover on the X button and press Enter are checked");
        return new DialogsPage();
    }
}
