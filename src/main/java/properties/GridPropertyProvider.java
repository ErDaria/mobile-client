package properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("grid")
public class GridPropertyProvider {
    private String host = "127.0.0.1";
    private String port = "4723";
}
