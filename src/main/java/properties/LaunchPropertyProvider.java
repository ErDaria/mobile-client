package properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class LaunchPropertyProvider {
    private String appPath = System.getenv("appPath");
    private boolean clearSystemFiles = true;
    private String downloadedAppPath = System.getenv("downloadedAppPath");
    private long implicitWaitDuration = 20;
    private long explicitWaitDuration = 10;
}
