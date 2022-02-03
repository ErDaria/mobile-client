package settings;

import lombok.Data;

@Data
public class Device {
    private String platform = System.getenv("platform");
    private String deviceName = System.getenv("deviceName");
    private String udid = System.getenv("udid");
}
