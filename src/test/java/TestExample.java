import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import screens.ServerConnectScreen;
import support.ui.Waiting;

import static screens.ServerConnectScreen.*;

public class TestExample extends TestBase {

    @Test
    public void test() throws InterruptedException {
        ServerConnectScreen.setAddressField("http://rndqa1/6.13/novacura.flow.server/");
//        WebElement pinOrAddressField = driver.findElementById("pinOrAddressField");
        WebElement connectButton = driver.findElementById("connectButton");
//        pinOrAddressField.sendKeys("http://rndqa1/6.13/novacura.flow.server/");
        connectButton.click();
        WebElement loginUsername = driver.findElementById("loginUsername");
        WebElement loginButton = driver.findElementById("loginButton");
        loginUsername.sendKeys("daerse");
        loginButton.click();
        Thread.sleep(5000);
    }
}
