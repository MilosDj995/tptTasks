import util.Base;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DialogsPage;
import util.TestDrive;

public class Task2Test extends Base {

    private DialogsPage homePage;
    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        homePage = new DialogsPage();
        testDrive = new TestDrive(homePage);
    }

    @Test
    public void taskTwo() {
        testDrive.runFunctionalTaskSecond();


    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }
}
