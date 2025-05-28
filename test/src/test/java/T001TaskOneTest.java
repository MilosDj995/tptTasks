import util.Base;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DialogsPage;
import util.TestDrive;

public class T001TaskOneTest extends Base {

    TestDrive testDrive = null;

    @BeforeEach
    public void setUpTest() {
        setUp();
        DialogsPage homePage = new DialogsPage();
        testDrive = new TestDrive(homePage);
    }

    @Test
    public void taskOne() {
        testDrive.runFunctionalTaskFirst();


    }

    @AfterEach
    public void tearDownTest() {
        tearDown();
    }
}
