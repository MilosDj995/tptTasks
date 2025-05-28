package util;

import Data.Sports;
import config.ConfigurationStrings;
import pages.DialogsPage;
import pages.DropdownPage;
import pages.GridPage;

public class TestDrive {
    private DialogsPage dialogHomePage = null;
    private GridPage gridHomePage = null;
    private DropdownPage dropdownHomePage = null;


    public TestDrive(DialogsPage dialogHomePage) {
        this.dialogHomePage = dialogHomePage;
    }

    public TestDrive(GridPage gridHomePage) {
        this.gridHomePage = gridHomePage;
    }

    public TestDrive(DropdownPage dropdownHomePage) {
        this.dropdownHomePage = dropdownHomePage;
    }

    public void runFunctionalTaskFirst() {
        dialogHomePage.openAndLoadDialogsHomePage(ConfigurationStrings.URL_DIALOGS)
                .clickOnOpenDialogButton()
                .verifyButtonsAreVisible()
                .verifyColorOfYesButton()
                .hoverOnXButtonAndPressEnter()
                .verifyDialogIsNotVisible();
    }

    public void runFunctionalTaskSecond() {
        dialogHomePage.openAndLoadDialogsHomePage(ConfigurationStrings.URL_DIALOGS)
                .clickOnOpenWindowDialogButton()
                .verifyNameOfDialog()
                .verifyMinimizeButtonOnAboutDialog()
                .verifyMaximizeButtonOnAboutDialog()
                .verifyCloseButtonOnAboutDialog()
                .maximizeAboutWindow()
                .closeAboutWindow();
    }

    public void runFunctionalTaskThird() {
        gridHomePage.openAndLoadGridHomePage(ConfigurationStrings.URL_GRID)
                .chooseUSEmployees()
                .printNameJobPhoneAndAddressOfEmployees()
                .chooseActiveEmployees()
                .checkActivityEmployees()
                .downloadExcelFile()
                .verifyExcelEmployeesNames()
                .deleteExcelFile();
    }

    public void runFunctionalTaskFourth() {
        dropdownHomePage.openAndLoadDropdownHomePage(ConfigurationStrings.URL_DROPDOWN)
                .chooseAutoCompleteSport(Sports.FOOTBALL)
                .verifyAutoCompleteSport(Sports.FOOTBALL)
                .clearAutoCompleteInputField();
    }

    public void runFunctionalTaskFifth() {
        dropdownHomePage.openAndLoadDropdownHomePage(ConfigurationStrings.URL_DROPDOWN)
                .verifyMultiSelectElement()
                .clearMultiSelectInputField()
                .chooseFootbalInMultiSelectInputField()
                .chooseTennisInMultiSelectInputField();
    }


}
