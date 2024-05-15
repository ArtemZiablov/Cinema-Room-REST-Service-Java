package cinema.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cinema")
public record CinemaProperties(
        int nRows,
        int nCols,
        String secret,
        int firstRows,
        int firstRowsPrice,
        int lastRowsPrice
) {
}
