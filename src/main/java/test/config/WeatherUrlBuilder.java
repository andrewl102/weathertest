package test.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration holder that encapsulates the logic of constructing a URL to the weather service.
 *
 */

@Configuration
public class WeatherUrlBuilder {
    private @Value("${weather.key}") String key;
    private @Value("${weather.baseUrl}") String baseUrl;
    private @Value("${weather.pathPrefix}") String pathPrefix;
    private @Value("${weather.extension}") String extension;

   public @Bean(name = "weatherUrlBuilder") WeatherUrlBuilder getWeatherUrlBuilder() {
       return this;
   }

    public String getUrlForZipcode(int zipCode) {
        return baseUrl + key+ "/"+pathPrefix+zipCode+"."+extension;
    }
}
