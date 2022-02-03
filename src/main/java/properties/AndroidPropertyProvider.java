package properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import io.appium.java_client.remote.AutomationName;

@Data
@ConfigurationProperties("android")
public class AndroidPropertyProvider {
    private String appPackage = "se.novacura.flow.ng";
    private String appWaitActivity = "crc64a79010b19294cf0a.UnconnectedViewActivity";
    private String automationName = AutomationName.ANDROID_UIAUTOMATOR2;
    private boolean autoGrantPermissions = true;
}
