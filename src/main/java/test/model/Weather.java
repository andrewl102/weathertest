package test.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    /*

{
	"response": {
		"version": "0.1"
		,"termsofService": "http://www.wunderground.com/weather/api/d/terms.html"
		,"features": {
		"conditions": 1
		}
	}
		,	"current_observation": {
		"image": {
		"url":"http://icons-ak.wxug.com/graphics/wu2/logo_130x80.png",
		"title":"Weather Underground",
		"link":"http://www.wunderground.com"
		},
		"display_location": {
		"full":"San Francisco, CA",
		"city":"San Francisco",
		"state":"CA",
		"state_name":"California",
		"country":"US",
		"country_iso3166":"US",
		"zip":"94117",
		"latitude":"37.77005005",
		"longitude":"-122.44140625",
		"elevation":"102.00000000"
		},
		"observation_location": {
		"full":"Castro/Eureka Valley, San Francisco, California",
		"city":"Castro/Eureka Valley, San Francisco",
		"state":"California",
		"country":"US",
		"country_iso3166":"US",
		"latitude":"37.758636",
		"longitude":"-122.437302",
		"elevation":"194 ft"
		},
		"estimated": {
		},
		"station_id":"KCASANFR138",
		"observation_time":"Last Updated on January 3, 11:34 PM PST",
		"observation_time_rfc822":"Thu, 03 Jan 2013 23:34:29 -0800",
		"observation_epoch":"1357284869",
		"local_time_rfc822":"Thu, 03 Jan 2013 23:49:19 -0800",
		"local_epoch":"1357285759",
		"local_tz_short":"PST",
		"local_tz_long":"America/Los_Angeles",
		"local_tz_offset":"-0800",
		"weather":"Clear",
		"temperature_string":"45.1 F (7.3 C)",
		"temp_f":45.1,
		"temp_c":7.3,
		"relative_humidity":"79%",
		"wind_string":"Calm",
		"wind_dir":"NW",
		"wind_degrees":317,
		"wind_mph":0.0,
		"wind_gust_mph":"4.0",
		"wind_kph":0.0,
		"wind_gust_kph":"6.4",
		"pressure_mb":"1025",
		"pressure_in":"30.27",
		"pressure_trend":"0",
		"dewpoint_string":"39 F (4 C)",
		"dewpoint_f":39,
		"dewpoint_c":4,
		"heat_index_string":"NA",
		"heat_index_f":"NA",
		"heat_index_c":"NA",
		"windchill_string":"45 F (7 C)",
		"windchill_f":"45",
		"windchill_c":"7",
		"feelslike_string":"45 F (7 C)",
		"feelslike_f":"45",
		"feelslike_c":"7",
		"visibility_mi":"10.0",
		"visibility_km":"16.1",
		"solarradiation":"0",
		"UV":"0.0",
		"precip_1hr_string":"0.00 in ( 0 mm)",
		"precip_1hr_in":"0.00",
		"precip_1hr_metric":" 0",
		"precip_today_string":"0.00 in (0 mm)",
		"precip_today_in":"0.00",
		"precip_today_metric":"0",
		"icon":"clear",
		"icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
		"forecast_url":"http://www.wunderground.com/US/CA/San_Francisco.html",
		"history_url":"http://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=KCASANFR138",
		"ob_url":"http://www.wunderground.com/cgi-bin/findweather/getForecast?query=37.758636,-122.437302"
	}



}

     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Observation {

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Location {
            private String city;
            private String state;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
        @JsonProperty("display_location")
        private Location displayLocation;

        public Location getDisplayLocation() {
            return displayLocation;
        }

        public void setDisplayLocation(Location displayLocation) {
            this.displayLocation = displayLocation;
        }

        @JsonProperty("temp_f")
        private String currentTemperature;

        public String getCurrentTemperature() {
            return currentTemperature;
        }

        public void setCurrentTemperature(String currentTemperature) {
            this.currentTemperature = currentTemperature;
        }
    }

    @JsonProperty("current_observation")
    private Observation currentObservation;



    public Observation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(Observation currentObservation) {
        this.currentObservation = currentObservation;
    }
}
