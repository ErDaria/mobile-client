import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import screens.ServerConnectScreen;
import support.ui.Waiting;

import static screens.ServerConnectScreen.*;

public class TestExample extends TestBase {

    @Test
    public void test() throws InterruptedException {
        ServerConnectScreen.setAddressField("http://cool.server/");
        WebElement AddressField = driver.findElementById("AddressField");
        WebElement connectButton = driver.findElementById("connectButton");
        AddressField.sendKeys("http://cool.server/");
        connectButton.click();
        WebElement loginUsername = driver.findElementById("loginUsername");
        WebElement loginButton = driver.findElementById("loginButton");
        loginUsername.sendKeys("username");
        loginButton.click();
        Thread.sleep(5000);
    }
}
