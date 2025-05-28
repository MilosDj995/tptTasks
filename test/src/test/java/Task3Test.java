import config.ConfigurationStrings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.GridPage;
import util.Base;
import util.TestDrive;

public class Task3Test extends Base {

    private GridPage homePage;
    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp(ConfigurationStrings.FILE_PATH);
        homePage = new GridPage();
        testDrive = new TestDrive(homePage);
    }

    @Test
    public void taskThree() {
        testDrive.runFunctionalTaskThird();


    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }
}
