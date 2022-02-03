package support.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import settings.DriverConfigurator;

@UtilityClass
public class ExpectedConditions {

    public ExpectedCondition<Boolean> keyboardIsShown() {
        AppiumDriver<RemoteWebElement> driver = DriverConfigurator.getDriver();
        return input -> {
            if (driver instanceof AndroidDriver) {
                return ((AndroidDriver<RemoteWebElement>) driver).isKeyboardShown();
            } else {
                return ((IOSDriver<RemoteWebElement>) driver).isKeyboardShown();
            }
        };
    }

    public ExpectedCondition<Boolean> isRunningInBackground(AppiumDriver<RemoteWebElement> driver, String bundleId) {
        return input -> driver.queryAppState(bundleId).equals(ApplicationState.RUNNING_IN_BACKGROUND);
    }
}
