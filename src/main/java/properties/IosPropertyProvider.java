package properties;

import io.appium.java_client.remote.AutomationName;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("ios")
public class IosPropertyProvider {
    private String automationName = AutomationName.IOS_XCUI_TEST;
}
