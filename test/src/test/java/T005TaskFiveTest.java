import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DropdownPage;
import util.Base;
import util.TestDrive;

public class T005TaskFiveTest extends Base {

    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        DropdownPage homePage = new DropdownPage();
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
