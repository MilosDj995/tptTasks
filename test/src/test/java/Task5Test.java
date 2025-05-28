import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;
import util.Base;
import util.TestDrive;

public class Task5Test extends Base {

    private DropdownPage homePage;
    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        homePage = new DropdownPage();
        testDrive = new TestDrive(homePage);
    }

    @Test
    public void taskFive() {
        testDrive.runFunctionalTaskFifth();


    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }
}
