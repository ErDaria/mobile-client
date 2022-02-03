package settings;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import properties.AndroidPropertyProvider;
import properties.IosPropertyProvider;
import properties.LaunchPropertyProvider;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_WAIT_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;


@Slf4j
public class CapabilitiesConfigurator {
    private static final LaunchPropertyProvider LAUNCH_PROPERTY_PROVIDER = new LaunchPropertyProvider();
    private static final AndroidPropertyProvider ANDROID_PROPERTY_PROVIDER = new AndroidPropertyProvider();
    private static final IosPropertyProvider IOS_PROPERTY_PROVIDER = new IosPropertyProvider();
    private static final Device DEVICE = new Device();

    public static DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(DEVICE_NAME, DEVICE.getDeviceName());
        capabilities.setCapability(UDID, DEVICE.getUdid());
        capabilities.setCapability(CLEAR_SYSTEM_FILES, LAUNCH_PROPERTY_PROVIDER.isClearSystemFiles());
        Platform platform = Platform.valueOf(DEVICE.getPlatform().toUpperCase());
        capabilities.setCapability(PLATFORM_NAME, platform);
        switch (platform) {
            case ANDROID:
                capabilities.merge(getAndroidCapabilities());
                break;
            case IOS:
                capabilities.merge(getIosCapabilities());
                break;
            default:
                throw new IllegalArgumentException("Platform is not supported! Check if you set ios or android on the parameter.");
        }
        log.info(capabilities.toString());
        return capabilities;
    }

    private static DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, ANDROID_PROPERTY_PROVIDER.getAutomationName());
        capabilities.setCapability(AUTO_GRANT_PERMISSIONS, ANDROID_PROPERTY_PROVIDER.isAutoGrantPermissions());
        capabilities.setCapability(APP_WAIT_ACTIVITY, ANDROID_PROPERTY_PROVIDER.getAppWaitActivity());
        capabilities.setCapability(APP, LAUNCH_PROPERTY_PROVIDER.getAppPath());
        return capabilities;
    }

    private static DesiredCapabilities getIosCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, IOS_PROPERTY_PROVIDER.getAutomationName());
        capabilities.setCapability(APP, LAUNCH_PROPERTY_PROVIDER.getAppPath());
        return capabilities;
    }
}
