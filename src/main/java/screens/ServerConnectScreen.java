package screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import io.appium.java_client.pagefactory.LocatorGroupStrategy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import support.ui.CommonActions;

public class ServerConnectScreen extends CommonActions {

    @HowToUseLocators(iOSXCUITAutomation = LocatorGroupStrategy.ALL_POSSIBLE)
    @AndroidFindBy(id = "pinOrAddressField")
    @iOSXCUITBy(id = "pinOrAddressField")
    private static MobileElement addressField;

    public static void setAddressField(String value) {
        tapInCenterOfElement(addressField);
        addressField.sendKeys(value);
    }
}
