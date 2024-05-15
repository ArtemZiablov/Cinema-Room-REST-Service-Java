package cinema.configs;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(CinemaProperties.class)
@Configuration
public class AppConfig {
}
