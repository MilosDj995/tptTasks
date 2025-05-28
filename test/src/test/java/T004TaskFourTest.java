import pages.DropdownPage;
import util.Base;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TestDrive;

public class T004TaskFourTest extends Base {

    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        DropdownPage homePage = new DropdownPage();
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
