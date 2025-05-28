package util;

import Constants.Constant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static util.LocatorUtil.loc;
import static util.LoggerManager.log;

public class Base {

    protected static WebDriver driver;

    public void openAndLoadHomePage(String url, By locator) {
        driver.get(url);
        waitForLoad(locator, 5, "Home page is not loaded");
        log("Home page is loaded succesfull");

    }

    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    public void setUp(String filePath) {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", filePath);
        prefs.put("download.prompt_for_download", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public boolean checkIfElementVisible(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void clickOnElement(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            throw new AssertionError("Unable to click.");
        }
    }

    public static void clickOnElement(By locator) {
        try {
            driver.findElement(locator).click();

        } catch (Exception e) {
            throw new AssertionError("Unable to click.");
        }
    }

    public static void clickOnElement(By locator, String errorText) {
        try {
            driver.findElement(locator).click();

        } catch (Exception e) {
            throw new AssertionError(errorText);
        }
    }

    public static void waitForLoad(By locator, int timeoutInSeconds) {
        waitForElement(locator, timeoutInSeconds, true);
    }

    public static void waitForLoad(By locator, int timeoutInSeconds, String errorText) {
        waitForElement(locator, timeoutInSeconds, true, errorText);
    }

    public void waitForNotVisible(By locator, int timeoutInSeconds) {
        waitForElement(locator, timeoutInSeconds, false);
    }
    public void waitForNotVisible(By locator, int timeoutInSeconds,String errorText) {
        waitForElement(locator, timeoutInSeconds, false,errorText);
    }
    public void verifyElementIsVisible(By locator, int timeoutInSeconds) {
        assertTrue(checkIfElementVisible(locator, timeoutInSeconds), "Element is not visible!!!");
    }

    public void verifyElementIsNotVisible(By locator, int timeoutInSeconds) {
        assertFalse(checkIfElementVisible(locator, timeoutInSeconds), "Element still visible!!!");
    }
    public void verifyElementIsVisible(By locator, int timeoutInSeconds, String errorMessage) {
        assertTrue(checkIfElementVisible(locator, timeoutInSeconds), errorMessage);
    }

    public void verifyElementIsNotVisible(By locator, int timeoutInSeconds, String errorMessage) {
        assertFalse(checkIfElementVisible(locator, timeoutInSeconds), errorMessage);
    }
    public static void waitForElement(By locator, int timeoutInSeconds, boolean isVisible) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        try {
            if (isVisible) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } else {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        } catch (TimeoutException e) {
            throw new AssertionError("Timeout while waiting for element.");
        }
    }

    public static void waitForElement(By locator, int timeoutInSeconds, boolean isVisible, String errorMessage) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeoutInSeconds);
        try {
            if (isVisible) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } else {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            }
        } catch (TimeoutException e) {
            throw new AssertionError(errorMessage);
        }
    }

    public void verifyColor(String expectedColor, By locator) {
        assertEquals(expectedColor, getElementColor(locator));
    }

    public String getElementColor(By locator) {
        WebElement element = driver.findElement(locator);
        return rgbaToHex(element.getCssValue("background-color"));
    }

    private String rgbaToHex(String rgba) {
        String[] parts = rgba.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");

        int r = Integer.parseInt(parts[0].trim());
        int g = Integer.parseInt(parts[1].trim());
        int b = Integer.parseInt(parts[2].trim());

        return String.format("#%02x%02x%02x", r, g, b);
    }

    public void hoverOnElement(By locator, String errorMessage) {
        try {
            Actions actions = new Actions(getDriver());
            WebElement element = getDriver().findElement(locator);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            throw new AssertionError(errorMessage);
        }
    }

    public void hoverOnElement(By locator) {
        try {
            Actions actions = new Actions(getDriver());
            WebElement element = getDriver().findElement(locator);
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            throw new AssertionError("Unable to hover on element");
        }
    }

    public void pressEnterOnActiveElement() {
        WebElement activeElement = getDriver().switchTo().activeElement();
        activeElement.sendKeys(Keys.ENTER);
    }

    public String getTextElement(By locator) {
        WebElement element = getDriver().findElement(locator);
        return element.getText();
    }

    public String getElementValue(By locator) {
        WebElement element = getDriver().findElement(locator);
        return element.getAttribute(Constant.VALUE);
    }

    public void verifyTextElement(By locator, String text) {
        assertEquals(getTextElement(locator), text);
    }
    private void verifyElementValue(By locator, String text) {
        assertEquals(getDriver().findElement(locator).getAttribute("value"), text);
    }
    public void enterInputField(By locator, String text) {
        clearInputField(locator);
        WebElement element = getDriver().findElement(locator);
        element.sendKeys(text);
        verifyElementValue(locator, text);
    }

    private void clearInputField(By locator) {
        WebElement element = getDriver().findElement(locator);
        element.clear();
    }

    public int numOfElements(By locator) {
        if (checkIfElementVisible(locator, 5)) {
            List<WebElement> elements = getDriver().findElements(locator);
            return elements.size();
        }
        return 0;
    }

    public String getElementAttribute(By locator, String attribute) {
        return getDriver().findElement(locator).getAttribute(attribute);
    }

    public void waitForFileDownload(String downloadDir, String fileName, int timeoutInSeconds) {
        File downloadedFile = new File(downloadDir + File.separator + fileName);
        File tempFile = new File(downloadDir + File.separator + fileName + ".crdownload");

        int waited = 0;
        while ((tempFile.exists() || !downloadedFile.exists()) && waited < timeoutInSeconds) {
            try {
                Thread.sleep(1000); // čekaj 1 sekundu
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            waited++;
        }
        assertTrue(downloadedFile.exists(), "File is NOT downloaded");
    }

    public void deleteDownloadedFile(String downloadDir, String fileName) {
        File fileToDelete = new File(downloadDir + File.separator + fileName);

        if (fileToDelete.exists()) {
            boolean deleted = fileToDelete.delete();
            assertTrue(deleted, "File is NOT deleted.");
        } else {
            System.out.println("File did NOT founded: " + fileToDelete.getAbsolutePath());
        }
    }

    public List<String> readFirstColumnFromThirdRow(int columnNum, int startIndex, String filePath) {
        List<String> elementsFromColumn = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = startIndex - 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell cell = row.getCell(columnNum - 1);
                    if (cell != null) {
                        elementsFromColumn.add(cell.getStringCellValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementsFromColumn;
    }

    public void verifyDropdownListElements(By actualListLocators, String locFirstElementByText, List<String> expectedList) {
        waitForLoad(loc(locFirstElementByText, expectedList.get(0)), 5);
        List<String> textElements = new ArrayList<>();
        List<WebElement> elements = getDriver().findElements(actualListLocators);
        for (int i = 0; i < elements.size(); i++) {
            textElements.add(elements.get(i).getText());
        }
        assertEquals(expectedList, textElements);
    }
}
