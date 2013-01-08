package test.service;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import test.config.WeatherUrlBuilder;
import test.model.Weather;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Spring {@link @Service} that encapsulates the retrieval of weather data and conversion to a data
 * model easily usable in the web application.
 */
@Service
public class WeatherRetrievalService {

    @Autowired
    @Qualifier(value = "weatherUrlBuilder")
    WeatherUrlBuilder builder;

    public Weather retrieveWeather(int zipCode) throws IOException {
//        String url = "http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/94117.json";
        String url = builder.getUrlForZipcode(zipCode);
        ObjectMapper mapper = new ObjectMapper(); // just need one

        /*return mapper.readValue(new File("/Users/andrewlynch/Downloads/git/test/src/main/java/test/service/file.json")
                , Weather.class);*/
        return mapper.readValue(new URL(url), Weather.class);
    }
}
