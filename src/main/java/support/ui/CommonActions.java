package support.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.RemoteWebElement;
import settings.DriverConfigurator;

@Slf4j
public class CommonActions {

    public static void hideKeyboard() {
        try {
            Waiting.until(ExpectedConditions.keyboardIsShown());
            DriverConfigurator.getDriver().hideKeyboard();
        } catch (Exception e) {
            log.info("Couldn't hide the keyboard");
        }
    }

    protected static void tapInCenterOfElement(MobileElement element){
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        new TouchAction(driver).tap(new PointOption().withCoordinates(element.getCenter())).perform();
    }
}
