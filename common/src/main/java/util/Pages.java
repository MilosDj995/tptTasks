package util;

import Constants.Constant;

import static util.LocatorUtil.loc;

public class Pages extends Base {
    public Pages() {
        super();
    }

    protected void waitForLoadPopup() {
        waitForLoad(loc("div_class", Constant.POPUP), 3);
    }
}
