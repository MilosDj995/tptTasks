package pages;

import constants.Constant;
import constants.TextElements;
import util.Dialog;

import static util.LoggerManager.*;
import static util.LocatorUtil.loc;


public class AboutDialog extends Dialog {
    public AboutDialog() {
        super();
        verifyDialogIsVisible();
    }

    public AboutDialog verifyMaximizeButtonOnAboutDialog() {
        verifyMaximizeButton();
        return this;
    }

    public AboutDialog verifyCloseButtonOnAboutDialog() {
        verifyCloseButton();
        return this;
    }

    public AboutDialog verifyMinimizeButtonOnAboutDialog() {
        verifyMinimizeButton();
        return this;
    }

    public AboutDialog verifyNameOfDialog() {
        verifyTextElement(loc("span_id", Constant.TITLE), TextElements.ABOUT_WINDOW_NAME);
        log("Dialog title has expected text");
        return this;
    }

    public AboutDialog verifyContent() {
        verifyTextElement(loc("div_class", Constant.CONTENT), TextElements.ADDITIONAL_INFO);
        log("Window content has expected text");
        return this;
    }

    public AboutDialog maximizeAboutWindow() {
        maximizeDialog();
        return this;
    }

    public DialogsPage closeAboutWindow() {
        closeDialog();
        log("About Window is Closed");
        return new DialogsPage();
    }

}
