package test.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import test.model.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-beans.xml", "classpath:dispatcher-servlet.xml"})
public class WeatherControllerTest  {
    @Autowired
    private WeatherController controller;


    @Test
    public void testValidWeather(){
        WeatherController.ZipCodeInformation information = new WeatherController.ZipCodeInformation();
        information.setZipCode("94117");
        MapBindingResult mapBindingResult = new MapBindingResult(new HashMap(), "zipcode");
        ExtendedModelMap model = new ExtendedModelMap();
        controller.post(information, mapBindingResult, model);

        Map targetMap = mapBindingResult.getTargetMap();
        Assert.assertEquals(0, mapBindingResult.getAllErrors().size());
        Weather weather = (Weather) model.get("weather");
        Assert.assertEquals("San Francisco", weather.getCurrentObservation().getDisplayLocation().getCity());

    }

    @Test
    public void testZipCodeThatDoesNotExist(){
        WeatherController.ZipCodeInformation information = new WeatherController.ZipCodeInformation();
        information.setZipCode("11111");
        MapBindingResult mapBindingResult = new MapBindingResult(new HashMap(), "zipcode");
        ExtendedModelMap model = new ExtendedModelMap();
        controller.post(information, mapBindingResult, model);
        List<ObjectError> allErrors = mapBindingResult.getAllErrors();
        Assert.assertEquals(1, allErrors.size());

        Assert.assertEquals("zipcode not found",allErrors.get(0).getDefaultMessage());
    }

    @Test
    public void testBadZipCode(){
        WeatherController.ZipCodeInformation information = new WeatherController.ZipCodeInformation();
        information.setZipCode("abcd");
        MapBindingResult mapBindingResult = new MapBindingResult(new HashMap(), "zipcode");
        ExtendedModelMap model = new ExtendedModelMap();
        controller.post(information, mapBindingResult, model);
        List<ObjectError> allErrors = mapBindingResult.getAllErrors();
        Assert.assertEquals(1, allErrors.size());

        Assert.assertEquals("Please enter a valid 5 digit zipcode",allErrors.get(0).getDefaultMessage());
    }

}
