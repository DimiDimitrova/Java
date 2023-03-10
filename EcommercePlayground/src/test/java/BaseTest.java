import org.example.core.Driver;
import org.example.core.WebCoreDriver;
import org.example.enums.Browser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    public static Driver driver;
    @BeforeAll
    public static void setUp() {
        driver = new WebCoreDriver();
        driver.start(Browser.CHROME);
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}