import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.remote.RemoteWebElement;
import settings.DriverConfigurator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestBase {

    private DriverConfigurator driverConfigurator;
    protected AppiumDriver<RemoteWebElement> driver;

    @BeforeAll
    public void setUp() {
        driver = driverConfigurator.getDriver();
    }

    @AfterAll
    public void teardown() {
        driver.quit();
    }
}
