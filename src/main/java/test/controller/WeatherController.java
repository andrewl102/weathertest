package test.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import test.model.Weather;
import test.service.WeatherRetrievalService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.IOException;

/**
 * Spring MVC {@link Controller} for displaying the weather conditions in a given zipcode.
 */
@Controller
public class WeatherController {
    @Autowired
    private WeatherRetrievalService weatherRetrievalService;

    private static final Logger logger = Logger.getLogger(WeatherController.class);

    @Autowired
    private Validator validator;

   /* @RequestMapping(value = "input")
    public String validateInput(Model model) {
        model.addAttribute("input", new ZipCodeInformation());
        return "input";
    }*/


    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelMap get() {
        return new ModelMap("zipcode", new ZipCodeInformation());
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String post(@ModelAttribute("zipcode") @Valid ZipCodeInformation userMsg,
                       BindingResult result, Model model) {

        validator.validate(userMsg, result);
        if (result.hasErrors()) { return "form"; }

        try {
            Weather weather = weatherRetrievalService.retrieveWeather(Integer.parseInt(userMsg.getZipCode()));
            if(weather.getCurrentObservation() == null) {
                result.addError(new FieldError("zipcode","zipCode","zipcode not found"));
                return "form";
            }
            model.addAttribute("weather",weather);
            return "show-weather";
        } catch (IOException e) {
            logger.error("Unable to retrieve weather",e);
            return "weather-down";
        }

    }

    public static class ZipCodeInformation {

        @Pattern(regexp = ("\\d\\d\\d\\d\\d"),message = "Please enter a valid 5 digit zipcode")
        private String zipCode;


        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }

}
