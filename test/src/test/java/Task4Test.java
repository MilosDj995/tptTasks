import pages.DropdownPage;
import util.Base;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestDrive;

public class Task4Test extends Base {

    private DropdownPage homePage;
    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        homePage = new DropdownPage();
        testDrive = new TestDrive(homePage);
    }

    @Test
    public void taskFour() {
        testDrive.runFunctionalTaskFourth();


    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }
}
