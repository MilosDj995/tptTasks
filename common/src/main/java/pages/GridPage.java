package pages;

import constants.Constant;
import data.Country;
import config.ConfigurationStrings;
import constants.TextElements;
import org.openqa.selenium.By;
import util.Pages;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.LocatorUtil.loc;
import static util.LoggerManager.log;

public class GridPage extends Pages {
    public GridPage openAndLoadGridHomePage(String url) {
        openAndLoadHomePage(url, loc("gridHomePage"));
        return this;
    }

    public GridPage chooseUSEmployees() {
        openCountryMenu();
        openFilterItem();
        validateFirstSearchOption();
        fillInFirstInputFieldFromFilter(Country.US);
        submitFilter();
        log("Filter 'US' is selected.");
        return this;
    }

    public GridPage chooseActiveEmployees() {
        openStatusMenu();
        openFilterItem();
        chooseIsTrueStatusFromFilter();
        submitFilter();
        log("Filter 'US' is selected.");
        return this;
    }

    private void openStatusMenu() {
        clickOnStatusMenu();
        waitForLoadPopup();
    }

    private void chooseIsTrueStatusFromFilter() {
        clickOnElement(loc("radioButtonFromStatusFilter", Constant.TRUE));
        waitForLoad(loc("checkedRadioButtonFromStatusFilter", Constant.TRUE), 3);
        waitForNotVisible(loc("disabledSubmitButton"), 3);

    }

    private void clickOnStatusMenu() {
        clickOnElement(loc("a_title", TextElements.STATUS));
    }

    private void openCountryMenu() {
        clickOnCountryMenu();
        waitForLoadPopup();
    }

    private void clickOnCountryMenu() {
        clickOnElement(loc("a_title", TextElements.COUNTRY));
    }

    private void openFilterItem() {
        if (!checkIfElementVisible(loc("expandedFilterButton"), 2)) {
            clickOnFilterItem();
            waitForLoad(loc("expandedFilterButton"), 3);
        }
    }

    private void validateFirstSearchOption() {
        if (!(getTextElement(loc("firstSearchOption")).equals(Constant.CONTAINS))) {
            openFirstSearchDropdown();
            chooseContainsOptionFromFilterDropdown();
        }
    }

    private void openFirstSearchDropdown() {
        clickOnElement(loc("firstSearchOptionDropdown"), "Unable to click on first search dropdown");
        waitForLoad(loc("firstSearchOptionDropdownContainer"), 3, "Unable to load first search dropdown container");
    }

    private void chooseContainsOptionFromFilterDropdown() {
        clickOnElement(loc("firstSearchOptionDropdownItem", Constant.CONTAINS_INDEX), "Unable to click on contains button from dropdown");
        waitForNotVisible(loc("firstSearchOptionDropdownContainer"), 5);
    }

    private void clickOnFilterItem() {
        clickOnElement(loc("filterButton"));
    }

    private void fillInFirstInputFieldFromFilter(String text) {
        enterInputField(loc("firstInputFieldFromFilter"), text);
        waitForNotVisible(loc("disabledSubmitButton"), 3);
    }

    private void submitFilter() {
        clickOnElement(loc("ableSubmitButton"));
        waitForNotVisible(loc("div_class", Constant.POPUP), 3);
    }

    public GridPage printNameJobPhoneAndAddressOfEmployees() {
        if (!checkIfRowElementsIsVisible()) return this;
        for (int i = 1; i <= getNumOfRowsFromTable(); i++) {
            log(i + ".Name: " + getName(i) + ", Job title: " + getJobTitle(i) + ", Phone Number: " + getPhoneNumber(i)
                    + ", Address: " + getAddress(i));
        }
        return this;
    }

    public GridPage checkActivityEmployees() {
        if (!checkIfRowElementsIsVisible()) return this;
        for (int i = 1; i <= getNumOfRowsFromTable(); i++) {
            checkEmployeeActiviti(i, TextElements.ONLINE);
        }
        log("All Employees are online");
        return this;
    }

    private void checkEmployeeActiviti(int i, String active) {
        assertEquals(active, getEmployeeActivity(i));

    }

    private String getEmployeeActivity(int i) {
        return getGridElement(i, TextElements.STATUS);
    }

    private boolean checkIfRowElementsIsVisible() {
        if (numOfElements(loc("tableRows")) > 0) return true;
        return false;
    }

    private int getNumOfRowsFromTable() {
        return numOfElements(loc("tableRows"));
    }

    private String getName(int i) {
        return getGridElement(i, TextElements.CONTACT_NAME);
    }

    private List<String> getNames() {
        List<String> employeesNames = new ArrayList<>();
        for (int i = 1; i <= getNumOfRowsFromTable(); i++) {
            employeesNames.add(getName(i));
        }
        return employeesNames;
    }

    private String getJobTitle(int i) {
        return getGridElement(i, TextElements.JOB);

    }

    private String getPhoneNumber(int i) {
        return getGridElement(i, TextElements.PHONE);

    }

    private String getAddress(int i) {
        return getGridElement(i, TextElements.ADDRESS);
    }

    private String getGridElement(int i, String column) {
        return getTextElement(loc("tableField", String.valueOf(i), getElementColindex(column)));
    }

    private String getElementColindex(String column) {
        return getElementAttribute(loc("a_title_ancestor_th", column), Constant.ARIA_COLINDEX);
    }

    public GridPage downloadExcelFile() {
        clickOnElement(loc("downloadExcelFileButton"));
        waitForFileDownload(ConfigurationStrings.FILE_PATH, ConfigurationStrings.FILE_NAME, 30);
        log("File is downloaded");
        return this;
    }

    public GridPage deleteExcelFile() {
        deleteDownloadedFile(ConfigurationStrings.FILE_PATH, ConfigurationStrings.FILE_NAME);
        log("File is deleted");
        return this;
    }

    public GridPage verifyExcelEmployeesNames() {
        assertEquals(getNames(), readFirstColumnFromThirdRow(1, 3, ConfigurationStrings.FILE_PATH + ConfigurationStrings.FILE_NAME), "The expected list of names in the excel file does NOT match the actual list");
        log("The expected list of names in the excel file match the actual list");
        return this;
    }

}
