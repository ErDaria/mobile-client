import org.springframework.boot.context.properties.EnableConfigurationProperties;
import properties.AndroidPropertyProvider;
import properties.GridPropertyProvider;
import properties.LaunchPropertyProvider;

@EnableConfigurationProperties({
        AndroidPropertyProvider.class,
        GridPropertyProvider.class,
        LaunchPropertyProvider.class
})
public class Application {
    public static void main(String[] args) {
    }
}
