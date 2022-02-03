package settings;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import properties.GridPropertyProvider;
import properties.LaunchPropertyProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class DriverConfigurator {
    private static final GridPropertyProvider GRID_PROPERTY_PROVIDER = new GridPropertyProvider();
    private static final CapabilitiesConfigurator CAPABILITIES_CONFIGURATOR = new CapabilitiesConfigurator();
    private static final LaunchPropertyProvider LAUNCH_PROPERTY_PROVIDER = new LaunchPropertyProvider();
    private static final Device DEVICE = new Device();

    @SneakyThrows(MalformedURLException.class)
    public static AppiumDriver<RemoteWebElement> getDriver() {
        DesiredCapabilities capabilities = CAPABILITIES_CONFIGURATOR.getCapabilities();
        URL driverUrl = new URL(format("http://%s:%s/wd/hub", GRID_PROPERTY_PROVIDER.getHost(), GRID_PROPERTY_PROVIDER.getPort()));
        AppiumDriver<RemoteWebElement> driver;
        Platform platform = Platform.valueOf(DEVICE.getPlatform().toUpperCase());
        switch (platform) {
            case IOS:
                driver = new IOSDriver<>(driverUrl, capabilities);
                break;
            case ANDROID:
                driver = new AndroidDriver<>(driverUrl, capabilities);
                break;
            default:
                throw new IllegalArgumentException("Platform is not supported! Check if you set ios or android on the parameter.");
        }
        driver.manage().timeouts().implicitlyWait(LAUNCH_PROPERTY_PROVIDER.getImplicitWaitDuration(), TimeUnit.SECONDS);
        return driver;
    }
}
